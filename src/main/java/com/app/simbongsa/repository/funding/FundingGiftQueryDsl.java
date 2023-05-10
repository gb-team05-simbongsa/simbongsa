package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.FundingGift;

import java.util.List;
import java.util.Optional;

public interface FundingGiftQueryDsl {
    //펀딩 아이템 조회
    public  Optional<FundingGift> findByIdDetail(Long fundingGiftId);

}
