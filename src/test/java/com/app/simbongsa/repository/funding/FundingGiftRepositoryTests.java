package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.FundingGift;
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
public class FundingGiftRepositoryTests {
    @Autowired
    FundingGiftRepository fundingGiftRepository;

    @Autowired
    FundingRepository fundingRepository;

    @Test
    public void saveTest() {
        for (int i = 1; i <= 8; i++) {
            FundingGift fundingGift = new FundingGift(
                    "펀딩선물" + i
                , 120 * i
                ,12000* i);
//                ,fundingRepository.findById(143L).get());
            fundingGiftRepository.save(fundingGift);
        }
    }

    // 조회하기
    @Test
    public void findByIdTest() {
        fundingGiftRepository.findById(773L);
    }
}
