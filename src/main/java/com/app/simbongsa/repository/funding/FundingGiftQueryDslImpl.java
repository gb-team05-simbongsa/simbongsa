package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.FundingGift;
import com.app.simbongsa.entity.funding.QFundingGift;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class FundingGiftQueryDslImpl implements FundingGiftQueryDsl {
    private final JPAQueryFactory query;

//    @Override
//    public Optional<FundingGift> findByIdDetail(Long fundingGiftId) {
//       return query.select(fundingGift)
//                .from(fundingGift)
//                .where(fundingGift.)
//    }


}
