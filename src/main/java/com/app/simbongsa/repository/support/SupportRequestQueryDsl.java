package com.app.simbongsa.repository.support;

import com.app.simbongsa.entity.support.SupportRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupportRequestQueryDsl {
    /* 유저별 후원 요청 조회 */
    public Page<SupportRequest> findByUserId(Pageable pageable, Long userId);
}
