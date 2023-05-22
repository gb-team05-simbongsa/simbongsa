package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.app.simbongsa.entity.funding.QFunding.funding;
import static com.app.simbongsa.entity.funding.QFundingGiftItem.fundingGiftItem;
import static com.app.simbongsa.entity.funding.QFundingItem.fundingItem;
import static com.app.simbongsa.entity.inquiry.QInquiry.inquiry;

@RequiredArgsConstructor
public class FundingItemQueryDslImpl implements FundingItemQueryDsl {
    private final JPAQueryFactory query;

    // 아이템 페이지 아이템 1개씩 조회
    @Override
    public Optional<FundingItem> findByIdItem_QueryDsl(Long itemId) {
       FundingItem foundItem = query.select(fundingItem)
                .from(fundingItem)
                .where(fundingItem.id.eq(itemId))
               .orderBy(fundingItem.id.desc())
                .fetchOne();
       return Optional.ofNullable(foundItem);

    }

    // 아이템 한개씩 삭제

    @Override
    public void deleteByFundingItemId(Long ItemId) {
            query.delete(fundingItem)
                    .where(fundingItem.id.eq(ItemId))
                    .execute();
    }

    @Override
    public FundingItem getCurrentSequence_QueryDsl() {
        return query.select(fundingItem)
                .from(fundingItem)
                .orderBy(fundingItem.id.desc())
                .limit(1)
                .fetchOne();
    }
}
