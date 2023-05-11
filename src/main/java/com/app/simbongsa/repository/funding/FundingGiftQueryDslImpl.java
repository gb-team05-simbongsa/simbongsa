package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.FundingGift;
import com.app.simbongsa.entity.funding.FundingItem;
import com.app.simbongsa.entity.funding.QFundingGift;
import com.app.simbongsa.entity.funding.QFundingItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.app.simbongsa.entity.funding.QFundingGift.fundingGift;
import static com.app.simbongsa.entity.funding.QFundingItem.fundingItem;

@RequiredArgsConstructor
public class FundingGiftQueryDslImpl implements FundingGiftQueryDsl {
    private final JPAQueryFactory query;

     @Override
    public List<FundingItem> findByIdGiftDetail(Long fundingGiftId) {

         return
                 query.select(fundingItem)
                .from(fundingGift)
                .join(fundingGift.fundingGiftItems)
                .fetchJoin()
                .where(fundingGift.id.eq(fundingGiftId))
                .fetch();
    }


}
