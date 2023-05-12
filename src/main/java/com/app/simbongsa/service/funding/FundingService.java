package com.app.simbongsa.service.funding;

import com.app.simbongsa.domain.FundingDTO;
import com.app.simbongsa.domain.VolunteerWorkDTO;
import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface FundingService {
    // 메인페이지 - 인기펀딩
    public List<FundingDTO> getAllPopularFundingList();

    //펀딩 전체 목록 조회
    public Slice<FundingDTO> getFundingList();

    // 펀딩 상세보기
    public FundingDTO getFundingDetail(Long id);

    default FundingDTO toFundingDTO(Funding funding) {
        return FundingDTO.builder()
                .fundingCategory(funding.getFundingCategory())
                .fundingCurrentPrice(funding.getFundingCurrentPrice())
                .fundingTargetPrice(funding.getFundingTargetPrice())
                .fundingTitle(funding.getFundingTitle())
                .fundingCreator(funding.getFundingCreator())
                .fundingPercent((int)((double)funding.getFundingCurrentPrice() / funding.getFundingTargetPrice() * 100))
                .build();
    }
}
