package com.app.simbongsa.repository.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReviewFileQueryDslImpl implements ReviewFileQueryDsl {
    private final JPAQueryFactory query;
}
