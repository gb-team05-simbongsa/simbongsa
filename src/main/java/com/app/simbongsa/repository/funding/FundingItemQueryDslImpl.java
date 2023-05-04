package com.app.simbongsa.repository.funding;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FundingItemQueryDslImpl implements FundingItemQueryDsl {
    private final JPAQueryFactory query;
}
