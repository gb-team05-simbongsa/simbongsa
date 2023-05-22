//package com.app.simbongsa.repository.funding;
//
//import com.app.simbongsa.entity.funding.FundingPayment;
//import com.app.simbongsa.entity.funding.QFundingPayment;
//import com.app.simbongsa.entity.support.Support;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//
//import java.util.List;
//
//import static com.app.simbongsa.entity.funding.QFundingPayment.fundingPayment;
//
//@RequiredArgsConstructor
//public class FundingPaymentQueryDslImpl implements FundingPaymentQueryDsl {
//    private final JPAQueryFactory query;
//
//    /* 내 펀딩 내역 조회 (페이징처리)*/
//    @Override
//    public Page<FundingPayment> findByMemberId(Pageable pageable, Long memberId) {
//        List<FundingPayment> foundFundingPayments = query.select(fundingPayment)
//                .from(fundingPayment)
//                .where(fundingPayment.member.id.eq(memberId))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        Long count = query.select(fundingPayment.count())
//                .from(fundingPayment)
//                .where(fundingPayment.member.id.eq(memberId))
//                .fetchOne();
//
//        return new PageImpl<>(foundFundingPayments,pageable,count);
//    }
//}
