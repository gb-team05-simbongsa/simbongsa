package com.app.simbongsa.repository.inquiry;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InquiryQueryDslImpl implements InquiryQueryDsl {
    private final JPAQueryFactory query;
}
