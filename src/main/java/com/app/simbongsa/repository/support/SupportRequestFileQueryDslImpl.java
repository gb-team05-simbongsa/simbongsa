package com.app.simbongsa.repository.support;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SupportRequestFileQueryDslImpl implements SupportRequestFileQueryDsl {
    private final JPAQueryFactory query;
}
