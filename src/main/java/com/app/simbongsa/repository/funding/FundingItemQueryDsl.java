package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.FundingGift;
import com.app.simbongsa.entity.funding.FundingGiftItem;
import com.app.simbongsa.entity.funding.FundingItem;

import java.util.List;
import java.util.Optional;

public interface FundingItemQueryDsl {

    // 아이템 페이지 아이템 1개씩 조회
    public FundingItem findByIdItem(Long fundingItemId);






}
