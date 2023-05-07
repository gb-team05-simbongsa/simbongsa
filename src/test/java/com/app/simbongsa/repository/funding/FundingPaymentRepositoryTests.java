package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.entity.funding.FundingPayment;
import com.app.simbongsa.entity.support.Support;
import com.app.simbongsa.repository.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class FundingPaymentRepositoryTests {

    @Autowired
    private FundingPaymentRepository fundingPaymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FundingRepository fundingRepository;

    @Test
    public void saveTest(){
        FundingPayment fundingPayment1 = new FundingPayment(300, LocalDateTime.now(), userRepository.findById(542L).get(),fundingRepository.findById(53L).get());
        fundingPaymentRepository.save(fundingPayment1);

        FundingPayment fundingPayment2 = new FundingPayment(500, LocalDateTime.now(), userRepository.findById(542L).get(),fundingRepository.findById(54L).get());
        fundingPaymentRepository.save(fundingPayment2);
    }

    /* 내 펀딩 내역 조회 (페이징처리)*/
    @Test
    public void findByUserIdTest(){
        PageRequest pageRequest = PageRequest.of(0,5);
        Page<FundingPayment> fundingPayments = fundingPaymentRepository.findByUserId(pageRequest, 542L);
        fundingPayments.stream().map(FundingPayment::toString).forEach(log::info);
        log.info("====================유저 아이디 542의 펀딩 내역수=================" + fundingPayments.getTotalElements());
    }
}
