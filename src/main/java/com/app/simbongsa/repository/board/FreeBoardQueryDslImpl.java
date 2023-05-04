package com.app.simbongsa.repository.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FreeBoardQueryDslImpl implements FreeBoardQueryDsl {
    private final JPAQueryFactory query;
}
