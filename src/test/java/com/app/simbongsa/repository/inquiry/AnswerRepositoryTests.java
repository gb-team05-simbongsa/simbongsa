//package com.app.simbongsa.repository.inquiry;
//
//import com.app.simbongsa.entity.inquiry.Answer;
//import com.app.simbongsa.entity.inquiry.Inquiry;
//import com.app.simbongsa.entity.volunteer.VolunteerWork;
//import com.app.simbongsa.repository.volunteer.VolunteerWorkRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//
//import javax.transaction.Transactional;
//
//@SpringBootTest
//@Transactional
//@Rollback(false)
//@Slf4j
//public class AnswerRepositoryTests {
//    @Autowired
//    private AnswerRepository answerRepository;
//
//    @Autowired
//    private InquiryRepository inquiryRepository;
//
//    @Test
//    public void saveTest() {
//        Answer answer = new Answer("제목2", "내용2", inquiryRepository.findById(24L).get());
//        answerRepository.save(answer);
//    }
//
//    /* 질문에 대한 답변 조회*/
//    @Test
//    public void findByInquiryIdTest(){
//        answerRepository.findByInquiryId_QueryDSL(422L);
//    }
//
//}
