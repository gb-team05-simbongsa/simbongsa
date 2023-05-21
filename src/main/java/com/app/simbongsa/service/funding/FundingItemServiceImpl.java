package com.app.simbongsa.service.funding;


import com.app.simbongsa.domain.FundingItemDTO;
import com.app.simbongsa.entity.funding.FundingItem;
import com.app.simbongsa.repository.funding.FundingItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("fundingItem") @Primary
public class FundingItemServiceImpl implements FundingItemService {
    private final FundingItemRepository fundingItemRepository;

    @Override
    public void ItemSave(FundingItemDTO fundingItemDTO) {
//        fundingItemDTO.setItemTitle(fundingItemDTO.getItemTitle());
//        fundingItemDTO.setItemContent(fundingItemDTO.getItemContent());

        fundingItemRepository.save(toFundingItemEntity(fundingItemDTO));

    }

   /* @Override
    public FundingItemDTO getItem(Long itemId) {
       return toFundingItemDTO(fundingItemRepository.findById(itemId).get());
    }*/
}
