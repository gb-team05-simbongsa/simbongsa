package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.FundingItem;
import com.app.simbongsa.entity.funding.QFundingItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.app.simbongsa.entity.funding.QFundingItem.fundingItem;

@RequiredArgsConstructor
public class FundingItemQueryDslImpl implements FundingItemQueryDsl {
    private final JPAQueryFactory query;


    QFundingItem qFundingItem = fundingItem;
    @Override
    public Optional<FundingItem> findById(Long fundingItemId) {
        return Optional.of(
                query.select(fundingItem)
                    .from(fundingItem)
                    .where(fundingItem.id.eq(fundingItemId))
                        .fetchOne());
    }
}
