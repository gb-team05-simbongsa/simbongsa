package com.app.simbongsa.repository.support;

import com.app.simbongsa.entity.support.Support;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupportQueryDsl {
    /* 내 후원 내역 조회 (페이징처리)*/
    public Page<Support> findByUserId(Pageable pageable, Long userId);
}
