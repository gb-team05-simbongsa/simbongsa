package com.app.simbongsa.repository.inquiry;

import com.app.simbongsa.domain.search.admin.AdminInquirySearch;
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
            Inquiry inquiry = new Inquiry("제목" + i, "내용" + i, userRepository.findById(145L).get());
            inquiryRepository.save(inquiry);
        }
    }

//    문의 전체 조회
    @Test
    public void findAllTest() {
        AdminInquirySearch adminInquirySearch = new AdminInquirySearch();
//        adminInquirySearch.setInquiryTitle("13");
        adminInquirySearch.setUserEmail("18");
        Page<Inquiry> inquiries = inquiryRepository.findAllWithPaging(adminInquirySearch, PageRequest.of(0, 5));

        inquiries.stream().map(Inquiry::toString).forEach(log::info);
        log.info("=======================" + inquiries.getTotalElements());
    }

//    문의 상세보기
    @Test
    public void findByIdTest() {
        inquiryRepository.findById(421L).ifPresent(inquiry -> log.info(inquiry.toString()));
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


    /* 유저별 문의 목록 조회 (페이징처리) */
    @Test
    public void findByUserIdTest(){
        PageRequest pageRequest = PageRequest.of(0,3);
        Page<Inquiry> inquiries = inquiryRepository.findByUserId(pageRequest, 6L);
        inquiries.stream().map(Inquiry::toString).forEach(log::info);
        log.info("====================유저 아이디 6의 후원요청목록수=================" + inquiries.getTotalElements());
    }
}
