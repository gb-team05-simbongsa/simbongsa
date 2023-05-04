package com.app.simbongsa.repository.funding;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FundingPaymentQueryDslImpl implements FundingPaymentQueryDsl {
    private final JPAQueryFactory query;
}
