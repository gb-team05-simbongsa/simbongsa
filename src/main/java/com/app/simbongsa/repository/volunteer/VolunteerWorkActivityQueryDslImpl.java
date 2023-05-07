package com.app.simbongsa.repository.volunteer;

import com.app.simbongsa.entity.volunteer.QVolunteerWork;
import com.app.simbongsa.entity.volunteer.QVolunteerWorkActivity;
import com.app.simbongsa.entity.volunteer.VolunteerWorkActivity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.app.simbongsa.entity.volunteer.QVolunteerWork.volunteerWork;
import static com.app.simbongsa.entity.volunteer.QVolunteerWorkActivity.volunteerWorkActivity;

@RequiredArgsConstructor
public class VolunteerWorkActivityQueryDslImpl implements VolunteerWorkActivityQueryDsl {
    private final JPAQueryFactory query;

//    신청 명단 조회
    @Override
    public List<VolunteerWorkActivity> findApplyByVolunteerWorkId(Long id) {
        return query.select(volunteerWorkActivity)
                .from(volunteerWorkActivity)
                .where(volunteerWorkActivity.volunteerWork.id.eq(id))
                .join(volunteerWorkActivity.user)
                .fetchJoin()
                .fetch();
    }
}
