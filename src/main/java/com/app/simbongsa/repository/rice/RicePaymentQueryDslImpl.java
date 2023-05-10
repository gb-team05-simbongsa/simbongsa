package com.app.simbongsa.repository.rice;


import com.app.simbongsa.search.admin.AdminPaymentSearch;

import com.app.simbongsa.entity.rice.RicePayment;
import com.app.simbongsa.type.RicePaymentType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static com.app.simbongsa.entity.rice.QRicePayment.ricePayment;

@RequiredArgsConstructor
public class RicePaymentQueryDslImpl implements RicePaymentQueryDsl {
    private final JPAQueryFactory query;


    //    공양미 충전 내역 전체 조회(페이징), 상태에 따른 변화
    @Override
    public Page<RicePayment> findByPaymentStatusWithPaging(AdminPaymentSearch adminPaymentSearch, RicePaymentType ricePaymentType, Pageable pageable) {
        BooleanExpression ricePaymentUsedEq = adminPaymentSearch.getRicePaymentUsed() == null ? null : ricePayment.ricePaymentUsed.eq(adminPaymentSearch.getRicePaymentUsed());
        BooleanExpression memberEmailLike = adminPaymentSearch.getMemberEmail() == null ? null : ricePayment.member.memberEmail.like("%" + adminPaymentSearch.getMemberEmail() + "%");
        BooleanExpression riceRefundRequestPriceLt = adminPaymentSearch.getRiceRefundRequestPrice() == null ? null : ricePayment.ricePaymentUsed.lt(adminPaymentSearch.getRiceRefundRequestPrice());


        List<RicePayment> foundRicePayment = query.select(ricePayment)
                .from(ricePayment)
                .where(ricePayment.ricePaymentStatus.eq(ricePaymentType))
                .where(ricePaymentUsedEq, memberEmailLike)
                .orderBy(ricePayment.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(ricePayment.count())
                .from(ricePayment)
                .where(ricePayment.ricePaymentStatus.eq(ricePaymentType))
                .fetchOne();

        return new PageImpl<>(foundRicePayment, pageable, count);
    }

    //    금일 결제 수 조회
    @Override
    public Long findByCreateDateToday() {
        Long count = query.select(ricePayment.count())
                .from(ricePayment)
                .where(ricePayment.createdDate.between(LocalDateTime.now().toLocalDate().atStartOfDay(), LocalDateTime.now().plusDays(1).toLocalDate().atStartOfDay()))
                .fetchOne();

        return count;
    }

    //    결제 총 금액 조회
    @Override
    public List<RicePayment> findAllPaymentTypeCharge() {
        return query.select(ricePayment)
                .from(ricePayment)
                .where(ricePayment.ricePaymentStatus.eq(RicePaymentType.충전))
                .fetch();
    }

    //    환전 요청 상태 승인으로 변경
    @Override
    public void updatePaymentStatusToAccessByIds(List<Long> ids) {
        query.update(ricePayment)
                .set(ricePayment.ricePaymentStatus, RicePaymentType.환전승인)
                .where(ricePayment.id.in(ids))
                .execute();
    }

    @Override
    public void updatePaymentByMemberId(Long id, int supportGongyang) {
        query.update(ricePayment)
                .set(ricePayment.member.memberRice, ricePayment.member.memberRice.subtract(supportGongyang))
                .set(ricePayment.ricePaymentUsed, supportGongyang)
                .where(ricePayment.member.id.eq(id))
                .execute();
    }

//    @Override
//    public void updatePaymentByMemberIdAndSupportGongyang(Long id, int supportGongyang) {
//        query.update(member)
//                .set(member.memberRice, member.memberRice.subtract(supportGongyang))
//                .set(ricePayment.member.id.)
//                .set(ricePayment.ricePaymentUsed, supportGongyang)
//                .where(member.id.eq(id))
//                .execute();
//    }
  /* 세션에 담긴 id 값 받아와서 내 공양미 조회(페이징) */
      @Override
      public Page<RicePayment> findByMemberId(Pageable pageable, Long memberId) {
          List<RicePayment> foundRice = query.select(ricePayment)
                  .from(ricePayment)
                  .where(ricePayment.member.id.eq(memberId))
                  .offset(pageable.getOffset())
                  .limit(pageable.getPageSize())
                  .fetch();

          Long count = query.select(ricePayment.count())
                  .from(ricePayment)
                  .where(ricePayment.member.id.eq(memberId))
                  .fetchOne();

          return new PageImpl<>(foundRice, pageable, count);
      }

}
