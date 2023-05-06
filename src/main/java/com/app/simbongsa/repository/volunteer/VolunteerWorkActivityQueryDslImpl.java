package com.app.simbongsa.repository.volunteer;

import com.app.simbongsa.entity.volunteer.QVolunteerWork;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.app.simbongsa.entity.volunteer.QVolunteerWork.volunteerWork;

@RequiredArgsConstructor
public class VolunteerWorkActivityQueryDslImpl implements VolunteerWorkActivityQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public List<VolunteerWork> findVolunteerWorkList() {
        return query.select(volunteerWork).from(volunteerWork).orderBy(volunteerWork.id.desc()).limit(8).fetch();
    }
}
