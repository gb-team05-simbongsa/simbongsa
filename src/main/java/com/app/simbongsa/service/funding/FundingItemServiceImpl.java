package com.app.simbongsa.service.funding;


import com.app.simbongsa.domain.FundingItemDTO;
import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.entity.funding.FundingItem;
import com.app.simbongsa.repository.funding.FundingItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Qualifier("fundingItem") @Primary
@Slf4j

public class FundingItemServiceImpl implements FundingItemService {
    private final FundingItemRepository fundingItemRepository;

    @Override
    public Long ItemSave(FundingItemDTO fundingItemDTO) {
//        fundingItemDTO.setItemTitle(fundingItemDTO.getItemTitle());
//        fundingItemDTO.setItemContent(fundingItemDTO.getItemContent());

        fundingItemRepository.save(toFundingItemEntity(fundingItemDTO));
        Long id = fundingItemRepository.getCurrentSequence_QueryDsl().getId();
        return id;
    }

    @Override
    public FundingItemDTO findByIdItem(Long itemId) {
        Optional<FundingItem> fundingItem = fundingItemRepository.findByIdItem_QueryDsl(itemId);

       return toFundingItemDTO(fundingItem.get());
    }

    // 아이템 현재 시퀀스 가져오기
    @Override
    public FundingItem getCurrentSequence() {
        return fundingItemRepository.getCurrentSequence_QueryDsl();

    }

    //삭제
    @Override
    @Transactional
    public void itemDelete(Long itemId) {
       fundingItemRepository.deleteByFundingItemId(itemId);
    }
}
