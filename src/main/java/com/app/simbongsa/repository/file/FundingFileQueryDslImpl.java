package com.app.simbongsa.repository.file;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FundingFileQueryDslImpl implements FundingFileQueryDsl {
    private final JPAQueryFactory query;
}
