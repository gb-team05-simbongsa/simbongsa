package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.Funding;

import java.util.List;

public interface FundingQueryDsl {
    public List<Funding> findAllWithPopularFunding();

}
