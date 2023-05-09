package com.app.simbongsa.repository.support;

import com.app.simbongsa.entity.support.Support;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SupportQueryDsl {
    /* 내 후원 내역 조회 (페이징처리)*/
    public Page<Support> findByMemberId(Pageable pageable, Long memberId);

    /* 후원 참여 내역 조회(페이징처리) - 후원 상세페이지 */
    public Page<Support> findAllSupportAttendWithMember_QueryDSL(Pageable pageable);

    /* 총 후원 참여 내역*/
    public Long findAllSupportAttend_QueryDSL();
}
