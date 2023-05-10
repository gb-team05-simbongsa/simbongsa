package com.app.simbongsa.repository.funding;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FundingGiftItemQueryDslImpl implements FundingGiftItemQueryDsl {
    private final JPAQueryFactory query;
}
