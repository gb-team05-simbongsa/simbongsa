package com.app.simbongsa.repository.supportRequest;

import com.app.simbongsa.entity.support.SupportRequest;
import com.app.simbongsa.entity.user.User;
import com.app.simbongsa.repository.support.SupportRequestRepository;
import com.app.simbongsa.repository.user.UserRepository;
import com.app.simbongsa.type.RequestType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class SupportRepositoryTests {
    @Autowired
    private SupportRequestRepository supportRequestRepository;

    @Autowired
    private UserRepository userRepository;

    /* 후원요청목록 save */
    @Test
    public void saveTest(){
        for (int i = 1; i <= 5; i++) {
            SupportRequest supportRequest = new SupportRequest("후원요청제목" + i,"후원요청내용" + i, RequestType.승인, userRepository.findById(5L).get());
            supportRequestRepository.save(supportRequest);
        }
        for (int i = 1; i <= 3; i++) {
            SupportRequest supportRequest = new SupportRequest("후원요청제목" + i,"후원요청내용" + i, RequestType.승인, userRepository.findById(6L).get());
            supportRequestRepository.save(supportRequest);
        }
        for (int i = 1; i <= 3; i++) {
            SupportRequest supportRequest = new SupportRequest("후원요청제목" + i,"후원요청내용" + i, RequestType.대기, userRepository.findById(6L).get());
            supportRequestRepository.save(supportRequest);
        }
    }

    /* 유저아이디로 후원요청목록 페이징처리해서 불러오기 */
    @Test
    public void findByUserIdTest(){
        PageRequest pageRequest = PageRequest.of(0,3);
        Page<SupportRequest> supportRequests = supportRequestRepository.findByUserId(pageRequest, 6L);
        supportRequests.stream().map(SupportRequest::toString).forEach(log::info);
        log.info("====================유저 아이디 6의 후원요청목록수=================" + supportRequests.getTotalElements());
    }
}
