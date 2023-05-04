package com.app.simbongsa.repository.support;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SupportQueryDslImpl implements SupportQueryDsl {
    private final JPAQueryFactory query;
}
