package com.app.simbongsa.repository.volunteer;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VolunteerWorkQueryDslImpl implements VolunteerWorkQueryDsl {
    private final JPAQueryFactory query;
}
