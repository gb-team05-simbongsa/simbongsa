package com.app.simbongsa.service.board;

import com.app.simbongsa.domain.FileDTO;
import com.app.simbongsa.domain.FreeBoardDTO;
import com.app.simbongsa.domain.FreeBoardReplyDTO;
import com.app.simbongsa.domain.NoticeDTO;
import com.app.simbongsa.domain.SupportRequestDTO;
import com.app.simbongsa.entity.board.FreeBoard;
import com.app.simbongsa.entity.board.FreeBoardReply;
import com.app.simbongsa.entity.inquiry.Notice;
import com.app.simbongsa.entity.support.SupportRequest;
import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.repository.board.FreeBoardFileRepository;
import com.app.simbongsa.repository.board.FreeBoardReplyRepository;
import com.app.simbongsa.repository.board.FreeBoardRepository;
import com.app.simbongsa.repository.member.MemberRepository;
import com.app.simbongsa.search.admin.AdminBoardSearch;
import com.app.simbongsa.search.admin.AdminNoticeSearch;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
                freeBoardFileRepository.save(toSuggestFileEntity(fileDTOS.get(i)));
            }
        }
    }

    /*댓글 저장*/
    @Override
    public void registerReply(FreeBoardReplyDTO freeBoardReplyDTO) {
//        memberRepository.findById(freeBoardReplyDTO.getId()).ifPresent(
//                member ->
//                        freeBoardRepository.findById(freeBoardReplyDTO.getId()).ifPresent(
//                                freeBoard -> {
//                                    FreeBoardReply freeBoardReply = FreeBoardReply
//                                }
//                        )
//        );
    }

    /*댓글 삭제*/
    @Override
    public void deleteReply(Long replyId) {

    }

    /*댓글 목록*/
    @Override
    public Slice<FreeBoardReplyDTO> getReplyList(Long freeBoardId, Pageable pageable) {
        return null;
    }

    /*댓글 갯수*/
   @Override
    public Integer getReplyCount(Long freeBoardId) {
        return null;
    }

    /*최신순 무한스크롤 전체 목록*/
    @Override
    public Slice<FreeBoardDTO> getNewList(Pageable pageable) {
        Slice<FreeBoard> freeBoards =
                freeBoardRepository.findAllByIdDescWithPaging_QueryDSL(PageRequest.of(0,10));
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
    public void write(FreeBoard freeBoard) {
        freeBoardRepository.save(freeBoard);
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
