package com.app.simbongsa.repository.volunteer;

import com.app.simbongsa.entity.volunteer.QVolunteerWork;
import com.app.simbongsa.entity.volunteer.QVolunteerWorkActivity;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.entity.volunteer.VolunteerWorkActivity;
import com.app.simbongsa.provider.UserDetail;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.app.simbongsa.entity.volunteer.QVolunteerWork.volunteerWork;
import static com.app.simbongsa.entity.volunteer.QVolunteerWorkActivity.volunteerWorkActivity;

@RequiredArgsConstructor
public class VolunteerWorkActivityQueryDslImpl implements VolunteerWorkActivityQueryDsl {
    private final JPAQueryFactory query;
    private final VolunteerWorkRepository volunteerWorkRepository;

//    신청 명단 조회
    @Override
    public Page<VolunteerWorkActivity> findApplyByVolunteerWorkId(Long id, Pageable pageable) {
        List<VolunteerWorkActivity> foundActivity = query.select(volunteerWorkActivity)
                .from(volunteerWorkActivity)
                .where(volunteerWorkActivity.volunteerWork.id.eq(id))
                .orderBy(volunteerWorkActivity.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(volunteerWorkActivity.count())
                .from(volunteerWorkActivity)
                .where(volunteerWorkActivity.volunteerWork.id.eq(id))
                .fetchOne();

        return new PageImpl<>(foundActivity, pageable, count);
    }

    /* 내 봉사 활동 목록 조회(페이징처리) */
    @Override
    public Page<VolunteerWorkActivity> findMyVolunteerById(Pageable pageable, Long memberId) {
        List<VolunteerWorkActivity> foundVolunteerWorkActivities = query.select(volunteerWorkActivity)
                .from(volunteerWorkActivity)
                .join(volunteerWorkActivity.volunteerWork)
                .fetchJoin()
                .where(volunteerWorkActivity.member.id.eq(memberId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(volunteerWorkActivity.count())
                .from(volunteerWorkActivity)
                .where(volunteerWorkActivity.member.id.eq(memberId))
                .fetchOne();

        return new PageImpl<>(foundVolunteerWorkActivities,pageable,count);
    }



}
