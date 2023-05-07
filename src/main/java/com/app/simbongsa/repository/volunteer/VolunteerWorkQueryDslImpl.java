package com.app.simbongsa.repository.volunteer;

import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.simbongsa.entity.volunteer.QVolunteerWork.volunteerWork;

@RequiredArgsConstructor
public class VolunteerWorkQueryDslImpl implements VolunteerWorkQueryDsl {
    private final JPAQueryFactory query;


    @Override
    public List<VolunteerWork> findVolunteerWorkList() {
        return query.select(volunteerWork).from(volunteerWork).orderBy(volunteerWork.id.desc()).limit(8).fetch();
    }

//    봉사활동 전체 조회(페이지)
    @Override
    public Page<VolunteerWork> findAllWithPaging(Pageable pageable) {
        List<VolunteerWork> foundVolunteerWork = query.select(volunteerWork)
                .from(volunteerWork)
                .orderBy(volunteerWork.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(volunteerWork.count())
                .from(volunteerWork)
                .fetchOne();

        return new PageImpl<>(foundVolunteerWork, pageable, count);
    }
}
