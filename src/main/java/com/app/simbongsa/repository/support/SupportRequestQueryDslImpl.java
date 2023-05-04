package com.app.simbongsa.repository.support;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SupportRequestQueryDslImpl implements SupportRequestQueryDsl {
    private final JPAQueryFactory query;
}
