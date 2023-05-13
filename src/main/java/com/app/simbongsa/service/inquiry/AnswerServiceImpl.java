package com.app.simbongsa.service.inquiry;

import com.app.simbongsa.domain.AnswerDTO;
import com.app.simbongsa.repository.inquiry.AnswerRepository;
import com.app.simbongsa.repository.inquiry.InquiryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("answer") @Primary
@Slf4j
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;
    private final InquiryRepository inquiryRepository;

    @Override
    public void saveAnswer(AnswerDTO answerDTO) {
        answerRepository.save(toAnswerEntity(answerDTO));
    }

    /* 질문에 대한 답변 조회*/
    @Override
    public AnswerDTO findByInquiryId(Long inquiryId) {
        return toAnswerDTO(answerRepository.findByInquiryId_QueryDSL(inquiryId).get());

    }
}
