package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.FundingGift;
import com.app.simbongsa.entity.funding.FundingGiftItem;
import com.app.simbongsa.entity.funding.FundingItem;

import java.util.List;
import java.util.Optional;

public interface FundingGiftQueryDsl {
     //  선물페이지에 등록된 선물 1개씩 조회
    public List<FundingGiftItem> findByIdGift(Long id);

    public  List<FundingGiftItem> findByIdGiftChoose(Long id);

}
