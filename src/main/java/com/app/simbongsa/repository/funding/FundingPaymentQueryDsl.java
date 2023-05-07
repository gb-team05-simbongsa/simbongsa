package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.FundingPayment;
import com.app.simbongsa.entity.support.Support;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FundingPaymentQueryDsl {
    /* 내 펀딩 내역 조회 (페이징처리)*/
    public Page<FundingPayment> findByUserId(Pageable pageable, Long userId);
}
