package com.app.simbongsa.repository.rice;

import com.app.simbongsa.domain.search.admin.AdminPaymentSearch;
import com.app.simbongsa.entity.rice.QRicePayment;
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
        BooleanExpression userEmailLike = adminPaymentSearch.getUserEmail() == null ? null : ricePayment.user.userEmail.like("%" + adminPaymentSearch.getUserEmail() + "%");
        BooleanExpression riceRefundRequestPriceLt = adminPaymentSearch.getRiceRefundRequestPrice() == null ? null : ricePayment.ricePaymentUsed.lt(adminPaymentSearch.getRiceRefundRequestPrice());


        List<RicePayment> foundRicePayment = query.select(ricePayment)
                .from(ricePayment)
                .where(ricePayment.ricePaymentStatus.eq(ricePaymentType))
                .where(ricePaymentUsedEq, userEmailLike)
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
    public void updatePaymentStatusToAccessById(Long id) {
        query.update(ricePayment)
                .set(ricePayment.ricePaymentStatus, RicePaymentType.환전승인)
                .where(ricePayment.id.eq(id))
                .execute();
    }

    @Override
    public void updatePaymentByUserId(Long id, int supportGongyang) {
        query.update(ricePayment)
                .set(ricePayment.user.userRice, ricePayment.user.userRice.subtract(supportGongyang))
                .set(ricePayment.ricePaymentUsed, supportGongyang)
                .where(ricePayment.user.id.eq(id))
                .execute();
    }

//
}
