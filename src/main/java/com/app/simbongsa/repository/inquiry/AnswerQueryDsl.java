package com.app.simbongsa.repository.inquiry;

import com.app.simbongsa.entity.inquiry.Answer;

import java.util.Optional;

public interface AnswerQueryDsl {

    /* 질문에 대한 답변 조회*/
    public Optional<Answer> findByInquiryId_QueryDSL(Long inquiryId);
}
