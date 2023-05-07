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
import org.springframework.data.domain.Slice;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Test
    public void findAllSupportRequest(){
        // 페이지 요청 설정
        PageRequest pageRequest = PageRequest.of(0, 3);

        // findAllSupportRequest() 메서드 호출
        Slice<SupportRequest> supportRequests = supportRequestRepository.findAllSupportRequest(pageRequest);

        // 조회된 데이터 출력
        supportRequests.stream().map(SupportRequest::toString).forEach(log::info);

        // 전체 후원 요청 목록 수 출력
//        Slice 객체에는 현재 페이지의 요소 목록과 다음 페이지가 있는지 여부를 확인할 수 있는 hasNext() 있는데,
//        전체 요소 수를 얻기 위해서는 Slice 객체를 List로 변환한 후에 List의 크기를 확인.
        List<SupportRequest> supportRequestList = supportRequests.getContent();
        long totalCount = supportRequests.hasNext() ? (pageRequest.getPageNumber() + 1) * pageRequest.getPageSize() : supportRequestList.size();
        log.info("==================== 전체 후원 요청 목록 수 ====================" + totalCount);

    }
}
