package com.app.simbongsa.repository.rice;

import com.app.simbongsa.search.admin.AdminPaymentSearch;
import com.app.simbongsa.entity.rice.RicePayment;
import com.app.simbongsa.type.RicePaymentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RicePaymentQueryDsl {

//    공양미 내역 전체 조회(페이징), 상태에 따른 변화
    public Page<RicePayment> findByPaymentStatusWithPaging(AdminPaymentSearch adminPaymentSearch, RicePaymentType ricePaymentType, Pageable pageable);

//    금일 결제 수 조회
    public Long findByCreateDateToday();

//    결제 총 금액 조회
    public List<RicePayment> findAllPaymentTypeCharge();

//    환전 요청 상태 승인으로 변경
    public void updatePaymentStatusToAccessById(Long id);

//    memberId로 조회해서 공양미 후원
    public void updatePaymentByMemberId(Long id, int supportGongyang);

    /* 세션에 담긴 id 값 받아와서 내 공양미 조회(페이징) */
    public Page<RicePayment> findByMemberId(Pageable pageable, Long memberId);
      
    public void updatePaymentByMemberIdAndSupportGongyang(Long id, int supportGonynag);

}
