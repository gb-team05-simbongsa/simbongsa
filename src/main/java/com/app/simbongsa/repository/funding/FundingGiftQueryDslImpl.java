package com.app.simbongsa.repository.funding;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FundingGiftQueryDslImpl implements FundingGiftQueryDsl {
    private final JPAQueryFactory query;
}
