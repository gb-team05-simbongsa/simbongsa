package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.entity.funding.QFunding;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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

//    
    @Override
    public Page<Funding> findAllWithPaging(Pageable pageable) {
        return null;
    }
}
