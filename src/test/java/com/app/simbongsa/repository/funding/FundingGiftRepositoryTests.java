package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.FundingGift;
import com.app.simbongsa.entity.funding.FundingGiftItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.app.simbongsa.entity.funding.QFundingGiftItem.fundingGiftItem;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class FundingGiftRepositoryTests {
    @Autowired
    FundingGiftRepository fundingGiftRepository;

    @Autowired
    FundingRepository fundingRepository;

    @Autowired
    FundingItemRepository fundingItemRepository;

    @Autowired
    FundingGiftItemRepository fundingGiftItemRepository;

    @Test
    public void saveTest() {
        for (int i = 1; i <= 20; i++) {
            FundingGift fundingGift = new FundingGift(
                    "펀딩선물" + i
                    , 120 * i
                    ,12000* i
                            ,fundingRepository.findById(235L).get());

            fundingGiftRepository.save(fundingGift);
        }
    }

    // 선택된 아이템 선물  조회하기 / 펀딩 후원하기에 findAll List로 select 하기
    @Test
    public void findByIdGiftTest() {
        fundingGiftRepository.findByIdGift(73L).stream().map(FundingGiftItem::toString).forEach(log::info);
    }


    @Test
    public  void findGiftChooseTest() {
       fundingRepository.findSupport_QueryDsl(444L);
    }

    @Test
    public void fundingDeleteTest() {
        fundingGiftRepository.delete(fundingGiftRepository.findById(74L).get());
    }
}