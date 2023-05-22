//package com.app.simbongsa.repository.funding;
//
//import com.app.simbongsa.entity.funding.*;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//
//import java.util.List;
//import java.util.Optional;
//
//import static com.app.simbongsa.entity.funding.QFunding.funding;
//import static com.app.simbongsa.entity.funding.QFundingGift.fundingGift;
//import static com.app.simbongsa.entity.funding.QFundingGiftItem.fundingGiftItem;
//import static com.app.simbongsa.entity.funding.QFundingItem.fundingItem;
//
//@RequiredArgsConstructor
//public class FundingGiftQueryDslImpl implements FundingGiftQueryDsl {
//    private final JPAQueryFactory query;
//
//    //  선물페이지에 등록된 선물 1개씩 조회
//    @Override
//    public List<FundingGiftItem> findByIdGift(Long id) {
//        return query.select(fundingGiftItem)
//                .from(fundingGiftItem)
//                .join(fundingGiftItem.fundingItem)
////                .join(fundingGiftItem.fundingGift)
//                .fetchJoin()
//                .fetch();
//
//    }
//
//    //내가 선택한 아이템 후원하기
//
//    @Override
//    public List<FundingGiftItem> findByIdGiftChoose(Long id) {
//        return query.select(fundingGiftItem)
//                .from(fundingGiftItem)
//                .rightJoin(fundingGiftItem.fundingGift)
//                .rightJoin(fundingGiftItem.fundingItem)
//                .fetchJoin()
//                .where(fundingGiftItem.fundingGift.id.eq(id))
//                .fetch();
//    }
//}
