package com.app.simbongsa.repository.funding;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FundingFileQueryDslImpl implements FundingFileQueryDsl {
    private final JPAQueryFactory query;


}
