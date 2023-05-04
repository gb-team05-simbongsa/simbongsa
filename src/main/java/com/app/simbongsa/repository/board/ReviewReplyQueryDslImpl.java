package com.app.simbongsa.repository.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReviewReplyQueryDslImpl implements ReviewReplyQueryDsl {
    private final JPAQueryFactory query;
}
