package com.app.simbongsa.repository.inquiry;

import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.search.admin.AdminBoardSearch;
import com.app.simbongsa.entity.inquiry.Inquiry;
import com.app.simbongsa.type.InquiryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

public interface InquiryQueryDsl {

//    문의 전체 조회(페이징 처리)
    public Page<Inquiry> findAllWithPaging(AdminBoardSearch adminBoardSearch, Pageable pageable);

//    답변 대기중, 답변 완료 개수 조회
    public Long findInquiryStatusCount(InquiryType inquiryType);

//    답변 대기를 답변 완료로 수정
    public void updateInquiryStatus(Long id);

    /* 유저별 문의 목록 조회 (페이징처리) */
    public Page<Inquiry> findByMemberId(Pageable pageable, @AuthenticationPrincipal UserDetail userDetail);

    /* 내 문의사항 수정 */
    public void updateMyInquiry(String inquiryTitle, String inquiryContent);

//    문의 상세보기
    public Inquiry findInquiryAndAnswerById(Long id);
}
