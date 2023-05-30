package com.app.simbongsa.repository.rice;

import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.search.admin.AdminPaymentSearch;
import com.app.simbongsa.entity.rice.RicePayment;
import com.app.simbongsa.type.RequestType;
import com.app.simbongsa.type.RicePaymentType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface RicePaymentQueryDsl {

//    공양미 내역 전체 조회(페이징), 상태에 따른 변화
    public Page<RicePayment> findByPaymentStatusWithPaging(AdminPaymentSearch adminPaymentSearch, RicePaymentType ricePaymentType,  Pageable pageable);

//    환전요청 페이지 조회(페이징)
    public Page<RicePayment> findByPaymentStatusWithPaging(AdminPaymentSearch adminPaymentSearch, RicePaymentType ricePaymentFirstType, RicePaymentType ricePaymentSecondType, Pageable pageable);

//    금일 결제 수 조회
    public Long findByCreateDateToday();

//    결제 총 금액 조회
    public List<RicePayment> findAllPaymentTypeCharge();

//    환전 요청 상태 승인으로 변경
    public void updatePaymentStatusToAccessByIds(List<Long> ids);

//    memberId로 조회해서 공양미 후원
    public void updatePaymentByMemberId(Long id, int supportGongyang);

    /* 내 공양미 조회(페이징) */
    public Page<RicePayment> findByMemberId(Pageable pageable, Long id);
      
//    public void updatePaymentByMemberIdAndSupportGongyang(Long id, int supportGonynag);

//    승인, 대기, 반려 수 조회
    public Long countStatusWaitAccessDenied(RicePaymentType ricePaymentType);

//    금일 결제 수 조회
    public Long countTodayPayment();

//    결제 총 금액
    public Long getAllPaymentPrice();

    /*후원받은 공양미 개수*/
    public Integer findSupportedRiceById(Long id);

    /*환전대기 공양미 개수*/
    public Integer findWaitRiceById(Long id);
}
