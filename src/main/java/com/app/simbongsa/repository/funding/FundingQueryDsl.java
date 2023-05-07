package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.Funding;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FundingQueryDsl {
    public List<Funding> findAllWithPopularFunding();

//    펀딩 전체 조회(페이징)
    public Page<Funding> findAllWithPaging(Pageable pageable);
}
