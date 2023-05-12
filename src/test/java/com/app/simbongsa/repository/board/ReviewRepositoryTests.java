package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.board.ReviewReply;
import com.app.simbongsa.entity.file.ReviewFile;
import com.app.simbongsa.search.admin.AdminBoardSearch;
import com.app.simbongsa.entity.board.Review;
import com.app.simbongsa.repository.member.MemberRepository;
import com.app.simbongsa.type.FileRepresentationalType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Optional;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class ReviewRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewReplyRepository reviewReplyRepository;

    @Autowired
    private ReviewFileRepository reviewFileRepository;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @PersistenceContext
    private EntityManager entityManager;

    /*후기게시판 등록*/
    @Test
    public void saveTest() {
        for(int i = 1; i <= 5; i++) {
            Review review = new Review("제목" + i, "내용" + i, memberRepository.findById(29L).get());
            reviewRepository.save(review);
        }
    }

    /*전체 조회 페이징*/
    @Test
    public void findAllWithPaging() {
        AdminBoardSearch adminBoardSearch = new AdminBoardSearch();
//        adminBoardSearch.setBoardTitle("3");
        adminBoardSearch.setMemberEmail("5");
        Page<Review> foundReview = reviewRepository.findAllWithPaging(adminBoardSearch, PageRequest.of(0, 5));
        foundReview.stream().map(review -> review.getBoardTitle()).forEach(log::info);
        log.info("=====================" + foundReview.getTotalElements());
    }

    /* 유저별 후기게시판 목록 조회 (페이징처리) */
    @Test
    public void findByMemberIdTest(){
        PageRequest pageRequest = PageRequest.of(0,4);
        Page<Review> reviews = reviewRepository.findByMemberId(pageRequest, 50L);
        reviews.stream().map(Review::toString).forEach(log::info);
        log.info("----------------------유저 542L의 리뷰게시물 목록 수 --------------------" + reviews.getTotalElements());
    }

//    리뷰게시판 댓글 등록
    @Test
    public void saveReplies(){
        for(int i =1; i<=2; i++){
            Optional<Review> byId = reviewRepository.findById(91L);
            ReviewReply reviewReply = new ReviewReply("댓글 테스트" + i, memberRepository.findById(29L).get(), byId.get());
            reviewReplyRepository.save(reviewReply);
        }
    }

//    댓글 삭제
    @Test
    public void deleteReplyTest() {
        reviewReplyRepository.delete(reviewReplyRepository.findById(3L).get());
    }

//    댓글 수
    @Test
    public void findReplyCountTest() {
        log.info("============================" + reviewReplyRepository.findReplyCountByReviewId(116L));
    }

//    리뷰게시판 파일 등록
    @Test
    public void saveFiles() {
        for(int i =1; i<=2; i++){
            Optional<Review> byId = reviewRepository.findById(115L);
            ReviewFile reviewFile = new ReviewFile("001.png","34271983dksjf","c:/2023/document", FileRepresentationalType.REPRESENTATION, reviewRepository.findById(116L).get());
            reviewFileRepository.save(reviewFile);
        }
    }

//    리뷰게시판 삭제(파일, 댓글도 한번에)
    @Test
    public void deleteTest() {
        reviewRepository.delete(reviewRepository.findById(115L).get());
    }

}
