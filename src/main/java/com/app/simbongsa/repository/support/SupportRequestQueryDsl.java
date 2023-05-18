package com.app.simbongsa.repository.support;

import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.search.admin.AdminSupportRequestSearch;
import com.app.simbongsa.entity.support.SupportRequest;
import com.app.simbongsa.type.RequestType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;
import java.util.Optional;

public interface SupportRequestQueryDsl {
    /* 유저별 후원 요청 조회 */
    public Page<SupportRequest> findByMemberId(Pageable pageable, @AuthenticationPrincipal UserDetail userDetail);
    // 후원 요청 목록
    public Slice<SupportRequest> findAllSupportRequest(Pageable pageable);

    /* 후원 요청 상세 페이지 조회*/
    public Optional<SupportRequest> findSupportRequestDetail_QueryDSL(Long id);

    // 후원 요청 전체 조회
    public Page<SupportRequest> findAllWithPaging(AdminSupportRequestSearch adminSupportRequestSearch, Pageable pageable);

    /* 후원 상세페이지, 후원 상세 정보 조회*/
    public SupportRequest findByIdWithSupportRequestInfo_QueryDsl(Long id);

    /* 후원 목록 페이지 검색(후원 많은 순, 후원 적은순, 최신순) */
    public Slice<SupportRequest> findByIdWithOrder(String keyword, Pageable pageable);
    public Page<SupportRequest> findAllWithPagingSearch(String keyword, Pageable pageable);

//    대기를 승인으로 변경
    public void updateWaitToAccessByIds(List<Long> ids, RequestType requestType);

//    대기 승인 반려 수 조회
    public Long countStatusWaitAccessDenied(RequestType requestType);
}
