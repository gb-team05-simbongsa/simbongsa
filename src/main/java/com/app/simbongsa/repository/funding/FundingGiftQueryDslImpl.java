package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.FundingGift;
import com.app.simbongsa.entity.funding.QFundingGift;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.app.simbongsa.entity.funding.QFundingGift.fundingGift;

@RequiredArgsConstructor
public class FundingGiftQueryDslImpl implements FundingGiftQueryDsl {
    private final JPAQueryFactory query;

     @Override
    public Optional<FundingGift> findByIdDetail(Long fundingItemId) {

         return Optional.of(
                 query.select(fundingGift)
                .from(fundingGift)
                 .join(fundingGift.fundingGiftItems)
                .where(fundingGift.id.eq(fundingItemId))
                .fetchOne());
    }


}
