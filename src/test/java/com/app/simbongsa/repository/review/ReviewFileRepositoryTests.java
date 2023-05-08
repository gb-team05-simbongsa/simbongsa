package com.app.simbongsa.repository.review;

import com.app.simbongsa.entity.board.Review;
import com.app.simbongsa.entity.file.ReviewFile;
import com.app.simbongsa.repository.board.ReviewFileRepository;
import com.app.simbongsa.repository.board.ReviewRepository;
import com.app.simbongsa.type.FileRepresentationalType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class ReviewFileRepositoryTests {
    @Autowired
    private ReviewFileRepository reviewFileRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void saveTest(){
        for (int i = 1; i <= 5; i++) {
            ReviewFile reviewFile = new ReviewFile("001.png","34271983dksjf","c:/2023/document", FileRepresentationalType.REPRESENTATION,reviewRepository.findById(1212L).get());
            reviewFileRepository.save(reviewFile);
        }
    }
}
