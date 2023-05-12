package com.app.simbongsa.service.funding;

import com.app.simbongsa.domain.FundingDTO;
import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.repository.funding.FundingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Qualifier("funding") @Primary
public class FundingServiceImpl implements FundingService {
    private final FundingRepository fundingRepository;

    @Override
    public List<FundingDTO> getAllPopularFundingList() {
        List<Funding> fundings = fundingRepository.findAllWithPopular();
        List<FundingDTO> fundingDTOS = new ArrayList<>();

        for(Funding funding : fundings){
            FundingDTO fundingDTO = toFundingDTO(funding);
            fundingDTOS.add(fundingDTO);
        }
        return fundingDTOS;
    }

    @Override
    public Slice<FundingDTO> getFundingList() {
    
    }

    @Override
    public FundingDTO getFundingDetail(Long id) {
        return null;
    }
}
