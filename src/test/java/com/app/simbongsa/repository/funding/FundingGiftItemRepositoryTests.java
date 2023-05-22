//package com.app.simbongsa.repository.funding;
//
//import com.app.simbongsa.entity.funding.FundingGiftItem;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//@SpringBootTest
//@Transactional
//@Rollback(false)
//@Slf4j
//public class FundingGiftItemRepositoryTests {
//
//    @Autowired
//    FundingGiftItemRepository fundingGiftItemRepository;
//
//    @Autowired
//    FundingGiftRepository fundingGiftRepository;
//
//    @Autowired
//    FundingItemRepository fundingItemRepository;
//
//    @Test
//            public void saveTest() {
//        for (int i = 1; i <= 10; i++) {
//            FundingGiftItem fundingGiftItem = new FundingGiftItem(
//                    fundingItemRepository.findById(425L).get()
//                    , fundingGiftRepository.findAll().get(i)
//            );
//            fundingGiftItemRepository.save(fundingGiftItem);
//        }
//    }
//
//
//}
