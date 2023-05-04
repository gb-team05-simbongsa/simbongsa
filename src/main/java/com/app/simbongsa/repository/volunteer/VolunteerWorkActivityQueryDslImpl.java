package com.app.simbongsa.repository.volunteer;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VolunteerWorkActivityQueryDslImpl implements VolunteerWorkActivityQueryDsl {
    private final JPAQueryFactory query;
}
