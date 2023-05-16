package com.app.simbongsa.service.funding;


import com.app.simbongsa.domain.FundingItemDTO;
import com.app.simbongsa.entity.funding.FundingItem;

public interface FundingItemService {

    // 아이템 등록
    public void ItemSave(FundingItemDTO fundingItemDTO);

    // 아이템 조회
//    public void getItem(Long itemId);


    // dto -> entity로 변환
    default FundingItem toFundingItemEntity(FundingItemDTO fundingItemDTO) {
        return FundingItem.builder()/*.id(fundingItemDTO.getId())*/
                .itemTitle(fundingItemDTO.getItemTitle())
                .itemContent(fundingItemDTO.getItemContent())
                .build();
    }



}
