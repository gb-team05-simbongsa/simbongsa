package com.app.simbongsa.repository.inquiry;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NoticeQueryDslImpl implements NoticeQueryDsl {
    private final JPAQueryFactory query;
}
