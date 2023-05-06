package com.app.simbongsa.repository.inquiry;

import com.app.simbongsa.entity.inquiry.Inquiry;
import com.app.simbongsa.repository.user.UserRepository;
import com.app.simbongsa.type.InquiryType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Arrays;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class InquiryRepositoryTests {
    @Autowired
    private InquiryRepository inquiryRepository;

    @Autowired
    private UserRepository userRepository;

//    더미데이터 넣기
    @Test
    public void saveTest() {
        for (int i = 1; i <= 20; i++) {
            Inquiry inquiry = new Inquiry("제목" + i, "내용" + i, userRepository.findById(5L).get());
            inquiryRepository.save(inquiry);
        }
    }

//    문의 전체 조회
    @Test
    public void findAllTest() {
        Page<Inquiry> inquiries = inquiryRepository.findAllWithPaging(PageRequest.of(0, 5));

        inquiries.stream().map(Inquiry::toString).forEach(log::info);
        log.info("=======================" + inquiries.getTotalElements());
    }

//    답변 대기중, 답변 완료 개수 조회
    @Test
    public void findInquiryStatusCountTest() {
        log.info("=======================" + inquiryRepository.findInquiryStatusCount(InquiryType.답변대기));
        log.info("=======================" + inquiryRepository.findInquiryStatusCount(InquiryType.답변완료));
    }

//    답변 대기를 답변 완료로 수정
    @Test
    public void updateInquiryStatusTest() {
        inquiryRepository.updateInquiryStatus(101L);
    }

//    문의 삭제
    @Test
    public void deleteTest() {
        Long[] ids = {101L, 102L};
        inquiryRepository.deleteAllById(Arrays.asList(ids));
    }
}
