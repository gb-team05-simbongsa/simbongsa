package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.board.Review;
import com.app.simbongsa.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class ReviewRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    /*후기게시판 등록*/
    @Test
    public void saveTest() {
        for(int i = 1; i <= 5; i++) {
            Review review = new Review("제목" + i, "내용" + i, userRepository.findById(542L).get());
            reviewRepository.save(review);
        }
    }

    /*전체 조회 페이징*/
    @Test
    public void findAllWithPaging() {
        Page<Review> foundReview = reviewRepository.findAllWithPaging(PageRequest.of(0, 5));
        foundReview.stream().map(Review::toString).forEach(log::info);
        log.info("=====================" + foundReview.getTotalElements());
    }


}
