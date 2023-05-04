package com.app.simbongsa.repository.file;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VolunteerWorkFileQueryDslImpl implements VolunteerWorkFileQueryDsl {
    private final JPAQueryFactory query;
}
