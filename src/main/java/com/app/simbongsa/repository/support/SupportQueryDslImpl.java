package com.app.simbongsa.repository.support;

import com.app.simbongsa.entity.support.Support;
import com.app.simbongsa.provider.UserDetail;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

import static com.app.simbongsa.entity.support.QSupport.support;

@RequiredArgsConstructor
public class SupportQueryDslImpl implements SupportQueryDsl {
    private final JPAQueryFactory query;

    /* 내 후원 내역 조회 (페이징처리)*/
    @Override
    public Page<Support> findByMemberId(Pageable pageable, Long id) {
        List<Support> foundSupports = query.select(support)
                .from(support)
                .where(support.member.id.eq(id))
                .orderBy(support.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(support.count())
                .from(support)
                .where(support.member.id.eq(id))
                .fetchOne();

        return new PageImpl<>(foundSupports,pageable,count);
    }
    /* 후원 참여 내역 조회(페이징처리) - 후원 상세페이지 */
    @Override
    public Page<Support> findAllSupportAttendWithMember_QueryDSL(Pageable pageable, Long id) {
        List<Support> foundSupports = query.select(support)
                .from(support)
                .join(support.member)
                .fetchJoin()
                .where(support.supportRequest.id.eq(id))
                .orderBy(support.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchJoin()
                .fetch();

        Long count = query.select(support.count())
                .from(support)
                .where(support.supportRequest.id.eq(id))
                .fetchOne();
        return new PageImpl<>(foundSupports, pageable, count);
    }

    /* 후원 총 참여 내역 수*/
    @Override
    public Long findAllSupportAttend_QueryDSL(Long id) {
        Long allSupportAttend = query.select(support.count())
                .from(support)
                .where(support.supportRequest.id.eq(id))
                .fetchOne();
        return allSupportAttend;
    }


    @Override
    public void updateSupportRequestCash(Support supportModify) {
        query.update(support).set(support.supportPrice, supportModify.getSupportPrice())
                .where(support.supportRequest.id.eq(supportModify.getId()))
                .execute();
    }
}
