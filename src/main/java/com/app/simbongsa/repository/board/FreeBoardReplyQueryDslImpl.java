package com.app.simbongsa.repository.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FreeBoardReplyQueryDslImpl implements FreeBoardReplyQueryDsl {
    private final JPAQueryFactory query;
}
