package com.app.simbongsa.repository.rice;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RicePaymentQueryDslImpl implements RicePaymentQueryDsl {
    private final JPAQueryFactory query;
}
