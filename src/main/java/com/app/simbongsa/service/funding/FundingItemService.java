package com.app.simbongsa.service.funding;


import com.app.simbongsa.domain.FundingItemDTO;
import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.entity.funding.FundingItem;

public interface FundingItemService {

    // 아이템 등록
    public Long ItemSave(FundingItemDTO fundingItemDTO);

    // 아이템 조회
    public FundingItemDTO findByIdItem(Long itemId);


    // 아이템 현재 시퀀스 가져오기
    public FundingItem getCurrentSequence();

    // 아이템 삭제
    public void itemDelete(Long itemId);


    default FundingItemDTO toFundingItemDTO(FundingItem fundingItem) {
        return FundingItemDTO.builder()
                .id(fundingItem.getId())
                .itemTitle(fundingItem.getItemTitle())
                .itemType(fundingItem.getItemType())
                .itemContent(fundingItem.getItemContent())
                .build();
    }


    // dto -> entity로 변환
    default FundingItem toFundingItemEntity(FundingItemDTO fundingItemDTO) {
        return FundingItem.builder()/*.id(fundingItemDTO.getId())*/
                .itemTitle(fundingItemDTO.getItemTitle())
                .itemType(fundingItemDTO.getItemType())
                .itemContent(fundingItemDTO.getItemContent())
                .build();
    }



}
