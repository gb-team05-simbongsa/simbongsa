package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.FundingGift;
import com.app.simbongsa.entity.funding.FundingItem;

import java.util.Optional;

public interface FundingItemQueryDsl {
    public Optional<FundingItem> findById(Long fundingItemId);

}
