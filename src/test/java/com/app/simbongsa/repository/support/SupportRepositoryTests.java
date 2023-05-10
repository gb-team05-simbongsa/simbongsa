package com.app.simbongsa.repository.support;

import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.entity.support.Support;
import com.app.simbongsa.repository.member.MemberRepository;
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
public class SupportRepositoryTests {
    @Autowired
    private SupportRepository supportRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private SupportRequestRepository supportRequestRepository;

    @Test
    public void saveTest(){
        Support support1 = new Support(9000,memberRepository.findById(80L).get(), supportRequestRepository.findById(121L).get());
        supportRepository.save(support1);

        Support support2 = new Support(12000,memberRepository.findById(81L).get(), supportRequestRepository.findById(129L).get());
        supportRepository.save(support2);

        Support support3 = new Support(17000,memberRepository.findById(79L).get(), supportRequestRepository.findById(126L).get());
        supportRepository.save(support3);
    }

    /* 내 후원 내역 조회 (페이징처리)*/
    @Test
    public void findByMemberIdTest(){
        PageRequest pageRequest = PageRequest.of(0,3);
        Page<Support> supports = supportRepository.findByMemberId(pageRequest, 542L);
        supports.stream().map(Support::toString).forEach(log::info);
        log.info("====================유저 아이디 542의 후원목록수=================" + supports.getTotalElements());
    }
    /* 후원 참여 내역 조회(페이징처리) - 후원 상세페이지 */
    @Test
    public void findAllSupportAttendWithMember_QueryDSLTest(){
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<Support> supports = supportRepository.findAllSupportAttendWithMember_QueryDSL(pageRequest);
        supports.stream().map(Support::toString).forEach(log::info);
        log.info("====================" + supports.getTotalElements() + "===========================");
    }

    /* 후원 총 참여 내역 수*/
    @Test
    public void findAllSupportAttend_QueryDSLTest() {
        log.info("================== 총 개수 : "+supportRepository.findAllSupportAttend_QueryDSL().toString() + " ============");
    }

//    해당 후원 명단 조회
    @Test
    public void findSupportByRequestIdTest() {
        memberRepository.findSupportByRequestId(129L).stream().map(Member::toString).forEach(log::info);
    }

//    공양미 후원하기



}
