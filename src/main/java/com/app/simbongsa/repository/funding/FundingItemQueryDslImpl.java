//package com.app.simbongsa.repository.funding;
//
//import com.app.simbongsa.entity.funding.FundingGiftItem;
//import com.app.simbongsa.entity.funding.FundingItem;
//import com.app.simbongsa.entity.funding.QFundingGiftItem;
//import com.app.simbongsa.entity.funding.QFundingItem;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//
//import java.util.List;
//import java.util.Optional;
//
//import static com.app.simbongsa.entity.funding.QFundingGiftItem.fundingGiftItem;
//import static com.app.simbongsa.entity.funding.QFundingItem.fundingItem;
//
//@RequiredArgsConstructor
//public class FundingItemQueryDslImpl implements FundingItemQueryDsl {
//    private final JPAQueryFactory query;
//
//    // 아이템 페이지 아이템 1개씩 조회
//    @Override
//    public FundingItem findByIdItem(Long fundingItemId) {
//        return
//                query.select(fundingItem)
//                    .from(fundingItem)
//                    .where(fundingItem.id.eq(fundingItemId))
//                        .fetchOne();
//    }
//
//
//
//}
