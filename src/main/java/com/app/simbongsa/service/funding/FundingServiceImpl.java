package com.app.simbongsa.service.funding;

import com.app.simbongsa.domain.FundingDTO;
import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.repository.funding.FundingRepository;
import com.app.simbongsa.search.admin.AdminFundingSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    return null;
    }
    @Override
    public FundingDTO getFundingDetail(Long id) {
        return null;
    }

    @Override
    public Page<FundingDTO> getFunding(Integer page, AdminFundingSearch adminFundingSearch) {
        Page<Funding> fundings = fundingRepository.findAllWithPaging(adminFundingSearch, PageRequest.of(page, 5));
        List<FundingDTO> fundingDTOS = fundings.getContent().stream().map(this::toFundingDTO).collect(Collectors.toList());
        return new PageImpl<>(fundingDTOS, fundings.getPageable(), fundings.getTotalElements());
    }
}
