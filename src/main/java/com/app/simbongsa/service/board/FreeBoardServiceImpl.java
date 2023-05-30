package com.app.simbongsa.service.board;

import com.app.simbongsa.domain.*;
import com.app.simbongsa.entity.board.FreeBoard;
import com.app.simbongsa.entity.board.FreeBoardReply;
import com.app.simbongsa.entity.file.FreeBoardFile;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.exception.UserNotFoundException;
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
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    /*수정*/
    @Override @Transactional
    public void update(FreeBoardDTO freeBoardDTO){
        List<FileDTO> fileDTOS = freeBoardDTO.getFileDTOS();

        freeBoardRepository.findById(freeBoardDTO.getId()).ifPresent(freeBoard -> {
            FreeBoard updateFreeBoard = FreeBoard.builder()
                    .id(freeBoard.getId())
                    .boardContent(freeBoardDTO.getBoardContent())
                    .boardTitle(freeBoardDTO.getBoardTitle())
                    .member(freeBoard.getMember())
                    .freeBoardReplyCount(freeBoard.getFreeBoardReplyCount())
                    .build();

            freeBoardRepository.save(updateFreeBoard);
        });

        freeBoardFileRepository.deleteById(freeBoardDTO.getId());

        if(fileDTOS != null){
            for (int i = 0; i < fileDTOS.size(); i++) {
                if(i == 0){
                    fileDTOS.get(i).setFileRepresentationalType(FileRepresentationalType.REPRESENTATION);
                }else {
                    fileDTOS.get(i).setFileRepresentationalType(FileRepresentationalType.NORMAL);
                }
                fileDTOS.get(i).setFreeBoard(getCurrentSequence());
                freeBoardFileRepository.save(toFreeBoardFileEntity(fileDTOS.get(i)));
            }
        }

    }

    /*삭제*/
    @Override @Transactional
    public void delete(Long freeBoardId){
        freeBoardRepository.findById(freeBoardId).ifPresent(
                freeBoard -> {
                    freeBoardReplyRepository.deleteByFreeBoardId(freeBoardId);
                    freeBoardRepository.delete(freeBoard);
                }
        );
    }



    /*마이페이지 게시물 목록 조회*/
    @Override
    public Page<FreeBoardDTO> getFreeForMemberIdList(Pageable pageable, Long id){
        Page<FreeBoard> freeBoards = freeBoardRepository.findAllByFreeMemberIdPaging_QueryDsl(pageable, id);
        List<FreeBoardDTO> freeBoardDTOS = freeBoards.stream().map(this::toFreeBoardDTO).collect(Collectors.toList());
        return new PageImpl<>(freeBoardDTOS, freeBoards.getPageable(), freeBoards.getTotalElements());
    }

    /*저장*/
    @Override
    public void register(FreeBoardDTO freeBoardDTO) {
        List<FileDTO> fileDTOS = freeBoardDTO.getFileDTOS();

//        memberRepository.findById(memberId).ifPresent(
//                member -> freeBoardDTO.setMemberDTO(toMemberDTO(member))
//        );

        FreeBoard freeBoard = toFreeBoardEntity(freeBoardDTO);
        freeBoardRepository.save(freeBoard);
        freeBoard.setMember(toMemberEntity(freeBoardDTO.getMemberDTO()));


        if (fileDTOS != null){
            for (int i = 0; i < fileDTOS.size(); i++){
                if (i == 0){
                    fileDTOS.get(i).setFileRepresentationalType(FileRepresentationalType.REPRESENTATION);
                }else {
                    fileDTOS.get(i).setFileRepresentationalType(FileRepresentationalType.NORMAL);
                }

                FreeBoardFile freeBoardFile = toFreeBoardFileEntity(fileDTOS.get(i));
                freeBoardFile.setFreeBoard(freeBoard);
                log.info(fileDTOS.get(0) + "================");
//                fileDTOS.get(i).setFreeBoard(getCurrentSequence());
                freeBoardFileRepository.save(freeBoardFile);
            }
        }
    }

    /*댓글 저장*/
    @Override @Transactional
    public void insertReply(ReplyRequestDTO replyRequestDTO) {
        memberRepository.findById(replyRequestDTO.getMemberId()).ifPresent(
                member -> {
                    freeBoardRepository.findById(replyRequestDTO.getBoardId()).ifPresent(
                            freeBoard -> {
                                FreeBoardReply freeBoardReply = FreeBoardReply.builder()
                                        .freeBoard(freeBoard)
                                        .member(member)
                                        .replyContent(replyRequestDTO.getReplyContent())
                                        .build();
                                freeBoardReplyRepository.save(freeBoardReply);
                                freeBoard.setFreeBoardReplyCount(getReplyCount(replyRequestDTO.getBoardId()));
                                freeBoardRepository.save(freeBoard);
                            }
                    );
                }
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
    public Slice<FreeBoardReplyDTO> getReplyList(Long freeBoardId, int page) {
        Slice<FreeBoardReply> freeBoardReplyList = freeBoardReplyRepository.findAllByFreeBoardReplyWithPaging(freeBoardId, PageRequest.of(page, 5));

        List<FreeBoardReplyDTO> freeBoardReplyDTOS = freeBoardReplyList.getContent().stream().map(this::toFreeBoardReplyDTO).collect(Collectors.toList());
        return new SliceImpl<>(freeBoardReplyDTOS, freeBoardReplyList.getPageable(), freeBoardReplyList.hasNext());
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
                freeBoardRepository.findAllByLikeCountDescWithPaging_QueryDSL(pageable);
        List<FreeBoardDTO> collect = freeBoards.get().map(board -> freeBoardToDTO(board)).collect(Collectors.toList());
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
    @Transactional
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


    // 메인페이지 인기게시판 목록 파일
    @Override
    public List<FreeBoardDTO> getAllWithFile() {
        List<FreeBoard> freeBoardList = freeBoardRepository.findAllWithFile();
        List<FreeBoardDTO> freeBoardDTOS = freeBoardList.stream()
                .map(freeBoard -> {
                    FreeBoardDTO freeBoardDTO = toFreeBoardDTO(freeBoard);
                    List<FreeBoardFile> freeBoardFiles = freeBoard.getFreeBoardFiles();
                    List<FileDTO> fileDTOs = FileToDTO(freeBoardFiles);
                    freeBoardDTO.setFileDTOS(fileDTOs);
                    return freeBoardDTO;
                }).collect(Collectors.toList());
        freeBoardDTOS.stream().map(FreeBoardDTO::toString).forEach(v -> log.info(v + " 여어어어어어어어익  ~~~~~"));
        return freeBoardDTOS;
    }

    // 메인페이지 인기게시판 목록
    @Override
    public List<FreeBoardDTO> getAllWithPopularFreeBoard() {
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
    public Page<FreeBoardDTO> getMyFreeBoards(Integer page, MemberDTO memberDTO) {
        page = page == null ? 0 : page;
        Page<FreeBoard> myFreeBoards = freeBoardRepository.findByMemberId(PageRequest.of(page, 5), memberDTO);

        List<FreeBoardDTO> myFreeBoardDTOS = myFreeBoards.stream().map(freeBoard -> {
            FreeBoardDTO freeBoardDTO = toFreeBoardDTO(freeBoard);
            List<FreeBoardFile> freeBoardFiles = freeBoard.getFreeBoardFiles();
            List<FileDTO> fileDTOs = FileToDTO(freeBoardFiles);
            freeBoardDTO.setFileDTOS(fileDTOs);
            return freeBoardDTO;
        }).collect(Collectors.toList());

        log.info("myFreeBoardDTOS serviceImpl: ===== " + myFreeBoardDTOS);
        return new PageImpl<>(myFreeBoardDTOS,myFreeBoards.getPageable(),myFreeBoards.getTotalElements());
    }


    /*인기순, 최신순, 인기 - 최신순 (쿼리가 맞는진 모름)*/
    @Override
    public Slice<FreeBoardDTO> getSlicePopular(Pageable pageable) {
        Slice<FreeBoard> freeBoardList = freeBoardRepository.findAllSliceByPopular(pageable);
        List<FreeBoardDTO> freeBoardDTOS = freeBoardList.getContent().stream().map(this::toFreeBoardDTO).collect(Collectors.toList());
        return new SliceImpl<>(freeBoardDTOS, pageable, freeBoardList.hasNext());
    }

    @Override
    public Slice<FreeBoardDTO> getSliceNew(Pageable pageable) {
        Slice<FreeBoard> freeBoardList = freeBoardRepository.findAllSliceByPopular(pageable);
        List<FreeBoardDTO> freeBoardDTOS = freeBoardList.getContent().stream().map(this::toFreeBoardDTO).collect(Collectors.toList());
        return new SliceImpl<>(freeBoardDTOS, pageable, freeBoardList.hasNext());
    }

    @Override
    public Slice<FreeBoardDTO> getSliceNewAndPopular(Pageable pageable) {
        Slice<FreeBoard> freeBoardList = freeBoardRepository.findAllSliceByPopular(pageable);
        List<FreeBoardDTO> freeBoardDTOS = freeBoardList.getContent().stream().map(this::toFreeBoardDTO).collect(Collectors.toList());
        return new SliceImpl<>(freeBoardDTOS, pageable, freeBoardList.hasNext());
    }

    @Override
    public Long getCount(Long id) {
        return freeBoardRepository.getReplyCount(id);
    }


}
