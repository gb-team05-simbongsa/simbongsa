package com.app.simbongsa.service.board;

import com.app.simbongsa.domain.FileDTO;
import com.app.simbongsa.domain.FreeBoardDTO;
import com.app.simbongsa.domain.FreeBoardReplyDTO;
import com.app.simbongsa.domain.ReplyDTO;
import com.app.simbongsa.entity.board.FreeBoard;
import com.app.simbongsa.entity.board.FreeBoardReply;
import com.app.simbongsa.entity.file.FreeBoardFile;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.exception.UserNotFoundException;
import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.repository.board.FreeBoardFileRepository;
import com.app.simbongsa.repository.board.FreeBoardReplyRepository;
import com.app.simbongsa.repository.board.FreeBoardRepository;
import com.app.simbongsa.repository.member.MemberRepository;
import com.app.simbongsa.search.admin.AdminBoardSearch;
import com.app.simbongsa.type.FileRepresentationalType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("freeBoard") @Primary
@Slf4j
public class FreeBoardServiceImpl implements FreeBoardService{
    private final FreeBoardRepository freeBoardRepository;
    private final MemberRepository memberRepository;
    private final FreeBoardReplyRepository freeBoardReplyRepository;
    private final FreeBoardFileRepository freeBoardFileRepository;

    /*저장*/
    @Override @Transactional
    public void register(FreeBoardDTO freeBoardDTO, Long memberId) {
        List<FileDTO> fileDTOS = freeBoardDTO.getFileDTOS();

        memberRepository.findById(memberId).ifPresent(
                member -> freeBoardDTO.setMemberDTO(toMemberDTO(member))
        );

        freeBoardRepository.save(toFreeBoardEntity(freeBoardDTO));
        if (fileDTOS != null){
            for (int i = 0; i < fileDTOS.size(); i++){
                if (1 == 0){
                    fileDTOS.get(i).setFileRepresentationalType(FileRepresentationalType.REPRESENTATION);
                }else {
                    fileDTOS.get(i).setFileRepresentationalType(FileRepresentationalType.NORMAL);
                }
                fileDTOS.get(i).setFreeBoard(getCurrentSequence());
                freeBoardFileRepository.save(toFreeBoardFileEntity(fileDTOS.get(i)));
            }
        }
    }

    /*댓글 저장*/
    @Override
    public void registerReply(FreeBoardReplyDTO freeBoardReplyDTO) {
        memberRepository.findById(freeBoardReplyDTO.getMemberId()).ifPresent(
                member ->
                        freeBoardRepository.findById(freeBoardReplyDTO.getBoardId()).ifPresent(
                                freeBoard -> {
                                    FreeBoardReply freeBoardReply = FreeBoardReply.builder()
                                            .freeBoard(freeBoard)
                                            .member(member)
                                            .replyContent(freeBoardReplyDTO.getReplyContent())
                                            .build();
                                    freeBoardReplyRepository.save(freeBoardReply);
                                    freeBoard.setFreeBoardReplyCount(getReplyCount(freeBoardReplyDTO.getBoardId()));
                                    freeBoardRepository.save(freeBoard);
                                }
                        )
        );
    }

    /*댓글 삭제*/
    @Override
    public void deleteReply(Long replyId) {
        freeBoardReplyRepository.findById(replyId).ifPresent(
                freeBoardReply -> {
                    freeBoardReplyRepository.delete(freeBoardReply);
                    freeBoardRepository.findById(freeBoardReply.getFreeBoard().getId()).ifPresent(
                            freeBoard -> {
                                freeBoard.setFreeBoardReplyCount(getReplyCount(replyId));
                                freeBoardRepository.save(freeBoard);
                            }
                    );
                }
        );
    }

    /*댓글 목록*/
    @Override
    public Slice<ReplyDTO> getReplyList(Long freeBoardId, Pageable pageable) {
        Slice<FreeBoardReply> freeBoardReplyList = freeBoardReplyRepository.findAllByFreeBoardReplyWithPaging(freeBoardId, pageable);

        List<ReplyDTO> replyDTOS = freeBoardReplyList.getContent().stream().map(this::toReplyDTO).collect(Collectors.toList());
        return new SliceImpl<>(replyDTOS, pageable, freeBoardReplyList.hasNext());
    }

    /*댓글 갯수*/
   @Override
    public Integer getReplyCount(Long freeBoardId) {
        return freeBoardReplyRepository.getReplyCount_QueryDsl(freeBoardId).intValue();
    }

    /*최신순 무한스크롤 전체 목록*/
    @Override
    public Slice<FreeBoardDTO> getNewList(Pageable pageable) {
        Slice<FreeBoard> freeBoards =
                freeBoardRepository.findAllByIdDescWithPaging_QueryDSL(pageable);
        List<FreeBoardDTO> collect = freeBoards.get().map(freeBoard -> freeBoardToDTO(freeBoard)).collect(Collectors.toList());

        return new SliceImpl<>(collect, pageable, freeBoards.hasNext());
    }

    /*인기순 무한스크롤 전체 목록*/
    @Override
    public Slice<FreeBoardDTO> getLikesList(Pageable pageable) {
        Slice<FreeBoard> freeBoards =
                freeBoardRepository.findAllByLikeCountDescWithPaging_QueryDSL(PageRequest.of(0,10));
        List<FreeBoardDTO> collect = freeBoards.get().map(freeBoard -> freeBoardToDTO(freeBoard)).collect(Collectors.toList());
        return new SliceImpl<>(collect, pageable, freeBoards.hasNext());
    }

    /*상세*/
    @Override
    public FreeBoardDTO getFreeBoard(Long freeBoardId) {
        Optional<FreeBoard> freeBoard = freeBoardRepository.findByIdForDetail_QueryDsl(freeBoardId);
        return toFreeBoardDTO(freeBoard.get());
    }

    /*시퀀스*/
    @Override
    public FreeBoard getCurrentSequence() {
        return freeBoardRepository.getCurrentSequence_QueryDsl();
    }

    /*작성*/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void write(FreeBoardDTO freeBoardDTO, Long memberId) {
        List<FileDTO> fileDTOS = freeBoardDTO.getFileDTOS();

        Member member = memberRepository.findById(memberId).orElseThrow(UserNotFoundException::new);

        FreeBoard freeBoard = toFreeBoardEntity(freeBoardDTO);
        freeBoard.setMember(member);
        freeBoardRepository.save(freeBoard);

        int count = 0;

        for (int i = 0; i < fileDTOS.size(); i++){
            if (fileDTOS.get(i) == null) continue;

            if (count == 0){
                fileDTOS.get(i).setFileRepresentationalType(FileRepresentationalType.REPRESENTATION);
                count++;
            }else {
                fileDTOS.get(i).setFileRepresentationalType(FileRepresentationalType.NORMAL);
            }

            fileDTOS.get(i).setFreeBoardDTO(freeBoardToDTO(getCurrentSequence()));
            FreeBoardFile freeBoardFile = toFreeBoardFileEntity(fileDTOS.get(i));

            freeBoardFile.setFreeBoard(freeBoard);
            freeBoardFileRepository.save(freeBoardFile);
        }
    }

    @Override
    public Page<FreeBoardDTO> getFreeBoard(Integer page, AdminBoardSearch adminBoardSearch) {
        Page<FreeBoard> freeBoards = freeBoardRepository.findAllWithPaging(adminBoardSearch, PageRequest.of(page, 5));
        List<FreeBoardDTO> freeBoardDTOS = freeBoards.getContent().stream().map(this::toFreeBoardDTO).collect(Collectors.toList());
        return new PageImpl<>(freeBoardDTOS, freeBoards.getPageable(), freeBoards.getTotalElements());
    }

    @Override
    public FreeBoardDTO getFreeBoardDetail(Long id) {
        return toFreeBoardDTO(freeBoardRepository.findById(id).get());
    }

    @Override
    public void deleteFreeBoardByIds(List<Long> ids) {
        freeBoardRepository.deleteAllById(ids);
    }

    @Override
    public List<FreeBoardDTO> findAllWithPopularFreeBoard() {
        List<FreeBoard> freeBoards = freeBoardRepository.findAllWithPopularFreeBoard();
        List<FreeBoardDTO> freeBoardDTOS = new ArrayList<>();
        for (FreeBoard freeBoard : freeBoards) {
            FreeBoardDTO freeBoardDTO = toFreeBoardDTO(freeBoard);
            freeBoardDTOS.add(freeBoardDTO);

        }
        return freeBoardDTOS;
    }

    /* 유저가 작성한 자유게시물 조회(페이징처리) */
    @Override
    public Page<FreeBoardDTO> getMyFreeBoards(Integer page, UserDetail userDetail) {
        page = page == null ? 0 : page;
        Page<FreeBoard> myFreeBoards = freeBoardRepository.findByMemberId(PageRequest.of(page, 5), userDetail);
        List<FreeBoardDTO> freeBoardDTOS = myFreeBoards.getContent().stream().map(this::toFreeBoardDTO).collect(Collectors.toList());
        return new PageImpl<>(freeBoardDTOS,myFreeBoards.getPageable(),myFreeBoards.getTotalElements());
    }
}
