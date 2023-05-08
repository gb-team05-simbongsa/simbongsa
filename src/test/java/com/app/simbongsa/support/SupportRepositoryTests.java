package com.app.simbongsa.support;

import com.app.simbongsa.entity.support.Support;
import com.app.simbongsa.repository.support.SupportRepository;
import com.app.simbongsa.repository.support.SupportRequestRepository;
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
public class SupportRepositoryTests {
    @Autowired
    private SupportRepository supportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SupportRequestRepository supportRequestRepository;

    @Test
    public void saveTest(){
        Support support1 = new Support(100,userRepository.findById(542L).get(), supportRequestRepository.findById(441L).get());
        supportRepository.save(support1);

        Support support2 = new Support(200,userRepository.findById(543L).get(), supportRequestRepository.findById(442L).get());
        supportRepository.save(support2);

        Support support3 = new Support(200,userRepository.findById(542L).get(), supportRequestRepository.findById(441L).get());
        supportRepository.save(support3);
    }

    /* 내 후원 내역 조회 (페이징처리)*/
    @Test
    public void findByUserIdTest(){
        PageRequest pageRequest = PageRequest.of(0,3);
        Page<Support> supports = supportRepository.findByUserId(pageRequest, 542L);
        supports.stream().map(Support::toString).forEach(log::info);
        log.info("====================유저 아이디 542의 후원목록수=================" + supports.getTotalElements());
    }
    /* 후원 참여 내역 조회(페이징처리) - 후원 상세페이지 */
    @Test
    public void findAllSupportAttendWithUser_QueryDSLTest(){
        PageRequest pageRequest = PageRequest.of(0, 5);
        Page<Support> supports = supportRepository.findAllSupportAttendWithUser_QueryDSL(pageRequest);
        supports.stream().map(Support::toString).forEach(log::info);
        log.info("====================" + supports.getTotalElements() + "===========================");
    }

    /* 후원 총 참여 내역 수*/
    @Test
    public void findAllSupportAttend_QueryDSLTest() {
        log.info("================== 총 개수 : "+supportRepository.findAllSupportAttend_QueryDSL().toString() + " ============");
    }

}
