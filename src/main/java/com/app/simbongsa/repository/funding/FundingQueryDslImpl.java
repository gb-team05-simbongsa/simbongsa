package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.entity.funding.QFunding;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.app.simbongsa.entity.funding.QFunding.funding;

@RequiredArgsConstructor
public class FundingQueryDslImpl implements FundingQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public List<Funding> findAllWithPopularFunding() {
        return query.selectFrom(funding)
                .orderBy(funding.fundingCurrentPrice.divide(funding.fundingTargetPrice).multiply(100).desc())
                .fetch();
    }

    //    펀딩 전체 조회(페이징)
    @Override
    public Page<Funding> findAllWithPaging(Pageable pageable) {
        List<Funding> foundFunding = query.select(funding)
                .from(funding)
                .orderBy(funding.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(funding.count())
                .from(funding)
                .fetchOne();

        return new PageImpl<>(foundFunding, pageable, count);
    }

    @Override
    public Optional<Funding> findByIdForDetail(Long fundingId) {
        return Optional.ofNullable(query.select(funding)
                .from(funding)
                .where(funding.id.eq(fundingId))
                .fetchOne());
    }
}
