package com.app.simbongsa.repository.support;

import com.app.simbongsa.entity.support.QSupport;
import com.app.simbongsa.entity.support.Support;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.simbongsa.entity.support.QSupport.support;

@RequiredArgsConstructor
public class SupportQueryDslImpl implements SupportQueryDsl {
    private final JPAQueryFactory query;

    /* 내 후원 내역 조회 (페이징처리)*/
    @Override
    public Page<Support> findByMemberId(Pageable pageable, Long memberId) {
        List<Support> foundSupports = query.select(support)
                .from(support)
                .where(support.member.id.eq(memberId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(support.count())
                .from(support)
                .where(support.member.id.eq(memberId))
                .fetchOne();

        return new PageImpl<>(foundSupports,pageable,count);
    }
    /* 후원 참여 내역 조회(페이징처리) - 후원 상세페이지 */
    @Override
    public Page<Support> findAllSupportAttendWithMember_QueryDSL(Pageable pageable) {
        List<Support> foundSupports = query.select(support)
                .from(support)
                .join(support.member)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchJoin()
                .fetch();

        Long count = query.select(support.count())
                .from(support)
                .fetchOne();
        return new PageImpl<>(foundSupports, pageable, count);
    }

    /* 후원 총 참여 내역 수*/
    @Override
    public Long findAllSupportAttend_QueryDSL() {
        Long allSupportAttend = query.select(support.count())
                .from(support)
                .fetchOne();
        return allSupportAttend;
    }

}
