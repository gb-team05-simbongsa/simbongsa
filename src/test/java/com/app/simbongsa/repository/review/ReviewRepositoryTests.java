package com.app.simbongsa.repository.review;

import com.app.simbongsa.entity.board.Review;
import com.app.simbongsa.repository.board.ReviewRepository;
import com.app.simbongsa.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class ReviewRepositoryTests {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MemberRepository userRepository;

    @Test
    public void saveTest() {
        for (int i = 1; i <= 5; i++) {
            Review review = new Review("후기게시판 제목" + i, "후기게시판 내용" + i, userRepository.findById(145L).get());
            reviewRepository.save(review);
        }

        for (int i = 6; i <= 10; i++) {
            Review review = new Review("후기게시판 제목" + i, "후기게시판 내용" + i, userRepository.findById(146L).get());
            reviewRepository.save(review);
        }
    }

    @Test
    public void findAllTest() {
        log.info(reviewRepository.findAll().toString());
    }

    /* 유저별 후기게시판 목록 조회 (페이징처리) */
    @Test
    public void findByUserIdTest(){
        PageRequest pageRequest = PageRequest.of(0,4);
        Page<Review> reviews = reviewRepository.findByUserId(pageRequest, 542L);
        reviews.stream().map(Review::toString).forEach(log::info);
        log.info("----------------------유저 542L의 리뷰게시물 목록 수 --------------------" + reviews.getTotalElements());
    }

    /*후기게시판 상세 조회*/
    @Test
    public void findByIdTest(){
        reviewRepository.findById(146L);
    }
}
