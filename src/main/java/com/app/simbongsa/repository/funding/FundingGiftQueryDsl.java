package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.FundingGift;
import com.app.simbongsa.entity.funding.FundingItem;

import java.util.List;
import java.util.Optional;

public interface FundingGiftQueryDsl {
    //펀딩 선물 조회
    public  List<FundingItem> findByIdGiftDetail(Long fundingGiftId);

}
