package com.app.simbongsa.repository.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FreeBoardFileQueryDslImpl implements FreeBoardFileQueryDsl {
    private final JPAQueryFactory query;
}
