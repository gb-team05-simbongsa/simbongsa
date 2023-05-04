package com.app.simbongsa.repository.funding;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FundingQueryDslImpl implements FundingQueryDsl {
    private final JPAQueryFactory query;
}
