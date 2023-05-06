package com.app.simbongsa.repository.rice;

import com.app.simbongsa.entity.rice.QRicePayment;
import com.app.simbongsa.entity.rice.RicePayment;
import com.app.simbongsa.type.RicePaymentType;
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

//    공양미 내역 전체 조회(페이징)
    @Override
    public Page<RicePayment> findByPaymentStatusWithPaging(Pageable pageable) {
        List<RicePayment> foundRicePayment = query.select(ricePayment)
                .from(ricePayment)
                .where(ricePayment.ricePaymentStatus.eq(RicePaymentType.충전))
                .orderBy(ricePayment.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(ricePayment.count())
                .from(ricePayment)
                .fetchOne();

        return new PageImpl<>(foundRicePayment, pageable, count);
    }

    @Override
    public Long findByCreateDateToday() {
        Long count = query.select(ricePayment.count())
                .from(ricePayment)
                .where(ricePayment.createdDate.between(LocalDateTime.now().toLocalDate().atStartOfDay(), LocalDateTime.now().plusDays(1).toLocalDate().atStartOfDay()))
                .fetchOne();

        return count;
    }
}
