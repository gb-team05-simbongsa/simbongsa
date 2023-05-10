package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.FundingPayment;
import com.app.simbongsa.repository.member.MemberRepository;
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
    private MemberRepository memberRepository;

    @Autowired
    private FundingRepository fundingRepository;

    @Test
    public void saveTest(){
        FundingPayment fundingPayment1 = new FundingPayment(300, LocalDateTime.now(), memberRepository.findById(9L).get(),fundingRepository.findById(143L).get());
        fundingPaymentRepository.save(fundingPayment1);

        FundingPayment fundingPayment2 = new FundingPayment(500, LocalDateTime.now(), memberRepository.findById(10L).get(),fundingRepository.findById(144L).get());
        fundingPaymentRepository.save(fundingPayment2);
    }

    /* 내 펀딩 내역 조회 (페이징처리)*/
    @Test
    public void findByMemberIdTest(){
        PageRequest pageRequest = PageRequest.of(0,5);
        Page<FundingPayment> fundingPayments = fundingPaymentRepository.findByMemberId(pageRequest, 542L);
        fundingPayments.stream().map(FundingPayment::toString).forEach(log::info);
        log.info("====================유저 아이디 542의 펀딩 내역수=================" + fundingPayments.getTotalElements());
    }
}
