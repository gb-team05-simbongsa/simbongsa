package com.app.simbongsa.repository.rice;

import com.app.simbongsa.entity.rice.RicePayment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RicePaymentQueryDsl {

//    공양미 내역 전체 조회(페이징)
    public Page<RicePayment> findByPaymentStatusWithPaging(Pageable pageable);

//    금일 결제 수 조회
    public Long findByCreateDateToday();
}
