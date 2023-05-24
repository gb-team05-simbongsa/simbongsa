package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.file.FreeBoardFile;
import com.app.simbongsa.entity.file.ReviewFile;
import com.app.simbongsa.type.FileRepresentationalType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

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
//        ReviewFile reviewFile1 = new ReviewFile("100" + ".png","48ew34ufeuods","c:/2023/document", FileRepresentationalType.REPRESENTATION,reviewRepository.findById(76L).get());
//        reviewFileRepository.save(reviewFile1);
        for (int i = 1; i <= 5; i++) {
            ReviewFile reviewFile2 = new ReviewFile(1300L,"00" + i + ".png","123" + i,"2023/04/28", FileRepresentationalType.NORMAL,reviewRepository.findById(1479L).get());
            reviewFileRepository.save(reviewFile2);
        }
    }
}
