package com.app.simbongsa.repository.inquiry;

import com.app.simbongsa.entity.inquiry.Answer;
import com.app.simbongsa.entity.inquiry.QAnswer;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.app.simbongsa.entity.inquiry.QAnswer.answer;
import static com.app.simbongsa.entity.volunteer.QVolunteerWork.volunteerWork;

@RequiredArgsConstructor
public class AnswerQueryDslImpl implements AnswerQueryDsl {
    private final JPAQueryFactory query;

    /* 질문에 대한 답변 조회*/
    @Override
    public Optional<Answer> findByInquiryId_QueryDSL(Long inquiryId) {
        return Optional.ofNullable(query.select(answer)
                .from(answer)
                .where(answer.inquiry.id.eq(inquiryId))
                .fetchOne());
    }

}
