package com.app.simbongsa.repository.rice;


import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.search.admin.AdminPaymentSearch;

import com.app.simbongsa.entity.rice.RicePayment;
import com.app.simbongsa.type.RequestType;
import com.app.simbongsa.type.RicePaymentType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.time.LocalDateTime;
import java.util.List;

import static com.app.simbongsa.entity.rice.QRicePayment.ricePayment;
import static com.app.simbongsa.entity.support.QSupportRequest.supportRequest;

@RequiredArgsConstructor
public class RicePaymentQueryDslImpl implements RicePaymentQueryDsl {
    private final JPAQueryFactory query;


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
                .where(ricePaymentUsedEq, memberEmailLike)
                .fetchOne();

        return new PageImpl<>(foundRicePayment, pageable, count);
    }

    //    공양미 충전 내역 전체 조회(페이징), 상태에 따른 변화
    @Override
    public Page<RicePayment> findByPaymentStatusWithPaging(AdminPaymentSearch adminPaymentSearch, RicePaymentType ricePaymentFirstType, RicePaymentType ricePaymentSecondType, Pageable pageable) {
        BooleanExpression ricePaymentUsedEq = adminPaymentSearch.getRicePaymentUsed() == null ? null : ricePayment.ricePaymentUsed.eq(adminPaymentSearch.getRicePaymentUsed());
        BooleanExpression memberEmailLike = adminPaymentSearch.getMemberEmail() == null ? null : ricePayment.member.memberEmail.like("%" + adminPaymentSearch.getMemberEmail() + "%");
        BooleanExpression riceRefundRequestPriceLt = adminPaymentSearch.getRiceRefundRequestPrice() == null ? null : ricePayment.ricePaymentUsed.lt(adminPaymentSearch.getRiceRefundRequestPrice());


        List<RicePayment> foundRicePayment = query.select(ricePayment)
                .from(ricePayment)
                .where(ricePayment.ricePaymentStatus.eq(ricePaymentFirstType).or(ricePayment.ricePaymentStatus.eq(ricePaymentSecondType)))
                .where(ricePaymentUsedEq, memberEmailLike)
                .orderBy(ricePayment.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(ricePayment.count())
                .from(ricePayment)
                .where(ricePayment.ricePaymentStatus.eq(ricePaymentFirstType).or(ricePayment.ricePaymentStatus.eq(ricePaymentSecondType)))
                .where(ricePaymentUsedEq, memberEmailLike)
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

    /* 내 공양미 조회(페이징) */
    @Override
    public Page<RicePayment> findByMemberId(Pageable pageable, UserDetail userDetail) {
        List<RicePayment> foundRice = query.select(ricePayment)
                .from(ricePayment)
                .join(ricePayment.member)
                .fetchJoin()
                .where(ricePayment.member.id.eq(userDetail.getId()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(ricePayment.count())
                .from(ricePayment)
                .where(ricePayment.member.id.eq(userDetail.getId()))
                .fetchOne();

        return new PageImpl<>(foundRice, pageable, count);
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

    @Override
    public Long countStatusWaitAccessDenied(RicePaymentType ricePaymentType) {
        return query.select(ricePayment.count())
                .from(ricePayment)
                .where(ricePayment.ricePaymentStatus.eq(ricePaymentType))
                .fetchOne();
    }

    @Override
    public Long countTodayPayment() {
        LocalDateTime todayStart = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0); // 오늘 날짜 시작 시간
        LocalDateTime todayEnd = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59); // 오늘 날짜 종료 시간

        return query.select(ricePayment.count())
                .from(ricePayment)
                .where(ricePayment.ricePaymentStatus.eq(RicePaymentType.충전).and(ricePayment.createdDate.between(todayStart, todayEnd)))
                .fetchOne();
    }

    @Override
    public Long getAllPaymentPrice() {
        return Long.valueOf(query.select(ricePayment.ricePaymentUsed.sum())
                  .from(ricePayment)
                  .where(ricePayment.ricePaymentStatus.eq(RicePaymentType.충전))
                  .fetchOne());
    }

}
