package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.FundingItem;
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
public class FundingItemRepositoryTests {
    @Autowired
    FundingItemRepository fundingItemRepository;

    @Autowired
    FundingRepository fundingRepository;

    @Test
    public void saveTest() {
        for(int i=1; i<=12; i++) {
            FundingItem fundingItem = new FundingItem(
                    "펀딩아이템" + i
             , "필름카메라2개"
             ,fundingRepository.findById(141L).get()
            );
         fundingItemRepository.save(fundingItem);
        }
    }

}
