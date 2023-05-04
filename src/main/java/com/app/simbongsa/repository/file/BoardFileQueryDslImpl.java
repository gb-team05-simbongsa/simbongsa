package com.app.simbongsa.repository.file;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardFileQueryDslImpl implements BoardFileQueryDsl {
    private final JPAQueryFactory query;
}
