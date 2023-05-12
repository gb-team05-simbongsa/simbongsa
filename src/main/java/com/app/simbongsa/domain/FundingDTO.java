package com.app.simbongsa.domain;

import com.app.simbongsa.entity.funding.FundingCreator;
import com.app.simbongsa.type.FundingCategoryType;
import com.app.simbongsa.type.RequestType;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FundingDTO {
    private Long id;
    private FundingCategoryType fundingCategory;
    private String fundingTitle;
    private String fundingShortTitle;
    private String fundingSummary;
    private int fundingTargetPrice;
    private int fundingCurrentPrice;
    private LocalDateTime fundingStartDate;
    private LocalDateTime fundingEndDate;
    private String fundingIntroduce;
    private String fundingBudgetExplain;
    private String fundingScheduleExplain;
    private String fundingGiftExplain;
    private RequestType fundingStatus;
    private FundingCreator fundingCreator;
    private int fundingPercent;


    @Builder
    public FundingDTO(FundingCategoryType fundingCategory, String fundingTitle, int fundingTargetPrice, int fundingCurrentPrice, FundingCreator fundingCreator, int fundingPercent) {
        this.fundingCategory = fundingCategory;
        this.fundingTitle = fundingTitle;
        this.fundingTargetPrice = fundingTargetPrice;
        this.fundingCurrentPrice = fundingCurrentPrice;
        this.fundingCreator = fundingCreator;
        this.fundingPercent = fundingPercent;
    }
}


