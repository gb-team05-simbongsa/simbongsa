package com.app.simbongsa.service.funding;

import com.app.simbongsa.domain.FundingDTO;
import com.app.simbongsa.domain.VolunteerWorkDTO;
import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.entity.funding.FundingCreator;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.search.admin.AdminFundingSearch;
import com.app.simbongsa.type.FundingCategoryType;
import com.app.simbongsa.type.RequestType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import java.time.LocalDateTime;
import java.util.List;

public interface FundingService {
    // 메인페이지 - 인기펀딩
    public List<FundingDTO> getAllPopularFundingList();

    //펀딩 전체 목록 조회
    public Slice<FundingDTO> getFundingList();

    // 펀딩 상세보기
    public FundingDTO getFundingDetail(Long id);

//    펀딩 전체 목록 조회
    public Page<FundingDTO> getFunding(Integer page, AdminFundingSearch adminFundingSearch);

//    default FundingDTO toFundingDTO(Funding funding) {
//        return FundingDTO.builder()
//                .fundingCategory(funding.getFundingCategory())
//                .fundingCurrentPrice(funding.getFundingCurrentPrice())
//                .fundingTargetPrice(funding.getFundingTargetPrice())
//                .fundingTitle(funding.getFundingTitle())
//                .fundingCreator(funding.getFundingCreator())
//                .fundingPercent((int)((double)funding.getFundingCurrentPrice() / funding.getFundingTargetPrice() * 100))
//                .build();
//    }

    default FundingDTO toFundingDTO(Funding funding) {
        return FundingDTO.builder()
                .id(funding.getId())
                .fundingCategory(funding.getFundingCategory())
                .fundingTitle(funding.getFundingTitle())
                .fundingShortTitle(funding.getFundingShortTitle())
                .fundingSummary(funding.getFundingSummary())
                .fundingTargetPrice(funding.getFundingTargetPrice())
                .fundingCurrentPrice(funding.getFundingCurrentPrice())
                .fundingStartDate(funding.getFundingStartDate())
                .fundingEndDate(funding.getFundingEndDate())
                .fundingIntroduce(funding.getFundingIntroduce())
                .fundingBudgetExplain(funding.getFundingBudgetExplain())
                .fundingScheduleExplain(funding.getFundingScheduleExplain())
                .fundingGiftExplain(funding.getFundingGiftExplain())
                .fundingStatus(funding.getFundingStatus())
                .fundingCreator(funding.getFundingCreator())
                .fundingPercent((int)((double)funding.getFundingCurrentPrice() / funding.getFundingTargetPrice() * 100))
                .build();
    }
}
