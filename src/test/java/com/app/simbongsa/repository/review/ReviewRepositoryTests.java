//package com.app.simbongsa.repository.review;
//
//import com.app.simbongsa.entity.board.Review;
//import com.app.simbongsa.entity.file.ReviewFile;
//import com.app.simbongsa.repository.board.ReviewFileRepository;
//import com.app.simbongsa.repository.board.ReviewRepository;
//import com.app.simbongsa.repository.member.MemberRepository;
//import com.app.simbongsa.type.FileRepresentationalType;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static com.app.simbongsa.entity.board.QReview.review;
//
//
//@SpringBootTest
//@Transactional
//@Rollback(false)
//@Slf4j
//public class ReviewRepositoryTests {
//    @Autowired
//    private ReviewRepository reviewRepository;
//
//    @PersistenceUnit
//    private EntityManagerFactory entityManagerFactory;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private ReviewFileRepository reviewFileRepository;
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Test
//    public void saveTest() {
//        for (int i = 1; i <= 5; i++) {
////            Review review = new Review("후기게시판 제목" + i, "후기게시판 내용" + i, memberRepository.findById(145L).get());
////            reviewRepository.save(review);
//        }
//
//        for (int i = 6; i <= 10; i++) {
////            Review review = new Review("후기게시판 제목" + i, "후기게시판 내용" + i, memberRepository.findById(146L).get());
////            reviewRepository.save(review);
//        }
//    }
//
//    @Test
//    public void findAllTest() {
//        log.info(reviewRepository.findAll().toString());
//    }
//
//    /* 유저별 후기게시판 목록 조회 (페이징처리) */
//    @Test
//    public void findByMemberIdTest(){
//        PageRequest pageRequest = PageRequest.of(0,4);
////        Page<Review> reviews = reviewRepository.findByMemberId(pageRequest, 542L);
////        reviews.stream().map(Review::toString).forEach(log::info);
////        log.info("----------------------유저 542L의 리뷰게시물 목록 수 --------------------" + reviews.getTotalElements());
//    }
//
//    /*후기게시판 상세 조회*/
//    @Test
//    public void findByIdTest(){
//        reviewRepository.findById(146L);
//    }
//
//    /* 마이페이지 작성한 후기게시물 상세 조회*/
//    @Test
//    public void findByIdForMyDetailTest(){
//        log.info("======================="+reviewRepository.findByIdForMyDetail(116L));
//    }
//
//    /*후기게시판 삭제(파일, 댓글도 한번에)*/
//    @Test
//    public void deleteTest() {
//        reviewRepository.delete(reviewRepository.findById(117L).get());
//    }
//
//}
