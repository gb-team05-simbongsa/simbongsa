package com.app.simbongsa.repository.board;

import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.entity.file.FreeBoardFile;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.search.admin.AdminBoardSearch;
import com.app.simbongsa.entity.board.FreeBoard;
import com.app.simbongsa.entity.board.FreeBoardReply;
import com.app.simbongsa.repository.member.MemberRepository;
import com.app.simbongsa.type.FileRepresentationalType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class FreeBoardRepositoryTests {
    @Autowired
    private FreeBoardRepository freeBoardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private FreeBoardReplyRepository freeBoardReplyRepository;

    @Autowired
    private FreeBoardFileRepository freeBoardFileRepository;

    /*자유게시판 등록*/
    @Test
    public void saveTest() {
            FreeBoard freeBoard = new FreeBoard("776이 작성한 자유게시판 제목","776이 작성한 자유게시물입니다. 잘 나오나요?",memberRepository.findMemberById(776L),null,null);
            freeBoardRepository.save(freeBoard);
    }

    @Test
    public void saveReplies() {
        for (int i = 1; i <= 5; i++) {
            Optional<FreeBoard> byId = freeBoardRepository.findById(46L);
            FreeBoardReply freeBoardReply = new FreeBoardReply("댓글 테스트" + i, memberRepository.findById(25L).get(), byId.get());
            freeBoardReplyRepository.save(freeBoardReply);
        }
    }

    /*전체 조회 페이징*/
    @Test
    public void findAllWithPaging() {
        AdminBoardSearch adminBoardSearch = new AdminBoardSearch();
//        adminBoardSearch.setBoardTitle("3");
        adminBoardSearch.setMemberEmail("5");
        Page<FreeBoard> foundFreeBoard = freeBoardRepository.findAllWithPaging(adminBoardSearch, PageRequest.of(0, 5));
        foundFreeBoard.stream().map(FreeBoard::toString).forEach(log::info);
        log.info("=====================" + foundFreeBoard.getTotalElements());
    }


    /* 내 자유게시물 목록 조회 (페이징처리) */
    @Test
    public void findByMemberIdTest() {
        PageRequest pageRequest = PageRequest.of(0, 4);
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(776L);
        Page<FreeBoard> freeBoards = freeBoardRepository.findByMemberId(pageRequest, memberDTO);
        freeBoards.stream().map(FreeBoard::toString).forEach(log::info);

        log.info("----------------------유저 776L의 리뷰게시판 목록 수 --------------------" + freeBoards.getTotalElements());
    }

    /* 자유게시판 상세 조회 */
    @Test
    public void findByIdTest() {
        freeBoardRepository.findById(50L).ifPresent(freeBoard -> log.info(freeBoard.getFreeBoardReplies().toString()));
//        log.info("----------------------유저 50L 자유게시판 목록 수 --------------------" + freeBoards.getTotalElements());
    }


    /* 자유게시판 인기순 목록 조회 - 무한스크롤 */
    @Test
    public void findAllByLikeCountDescWithPaging_QueryDSLTest() {
        freeBoardRepository.findAllByLikeCountDescWithPaging_QueryDSL(
                PageRequest.of(0, 3)
        ).stream().map(FreeBoard::toString).forEach(log::info);
    }

    /* 자유게시판 최신순 목록 조회 - 무한스크롤 */
    @Test
    public void findAllByIdDescWithPaging_QueryDSLTest() {
        freeBoardRepository.findAllByIdDescWithPaging_QueryDSL(PageRequest.of(0, 2)).stream().map(FreeBoard::toString).forEach(log::info);
    }

    /*자유게시판 삭제(파일, 댓글도 한번에)*/
    @Test
    public void deleteTest() {
        freeBoardRepository.deleteAllById(Arrays.asList(187L,186L,185L,184L,183L,182L,181L,180L));
    }


    /* 마이페이지 작성한 자유게시물 상세 조회*/
    @Test
    public void findByIdForMyDetail() {
        Optional<FreeBoard> myFreeBoard = freeBoardRepository.findById(105L);
        myFreeBoard.ifPresent(freeBoard -> log.info("====================================" + freeBoard.getFreeBoardFiles().toString() + "====================="));
    }

    //    댓글 삭제
    @Test
    public void deleteReplyTest() {
        freeBoardRepository.delete(freeBoardRepository.findById(3L).get());
    }

    //    댓글 수
    @Test
    public void findReplyCountTest() {
        log.info("============================" + freeBoardRepository.findById(116L));
    }

//   메인페이지 목록뽑기
    @Test
    public void findAllWithPopularFreeBoardTest(){
//        freeBoardRepository.findAllWithPopularFreeBoard().stream().map(FreeBoard::getFreeBoardReplies).map(v->v.size()).forEach(v -> log.info(v.toString()));
        freeBoardRepository.findAllWithPopularFreeBoard().stream().map(FreeBoard::getBoardTitle).forEach(v -> log.info(v +"여기야~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"));

    }
    // 메인페이지 파일이미지 뽑기
    @Test
    public void findAllWithFileTest(){
        freeBoardRepository.findAllWithFile().stream().map(FreeBoard::getFreeBoardFiles).forEach(v -> log.info(v.toString() +"여기야~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"));
    }
}
