package com.app.simbongsa.repository.inquiry;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AnswerQueryDslImpl implements AnswerQueryDsl {
    private final JPAQueryFactory query;
}
