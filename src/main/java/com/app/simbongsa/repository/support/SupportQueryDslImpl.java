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
    public Page<Support> findByUserId(Pageable pageable, Long userId) {
        List<Support> foundSupports = query.select(support)
                .from(support)
                .where(support.user.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(support.count())
                .from(support)
                .where(support.user.id.eq(userId))
                .fetchOne();

        return new PageImpl<>(foundSupports,pageable,count);
    }
}
