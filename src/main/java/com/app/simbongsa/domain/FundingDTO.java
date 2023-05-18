package com.app.simbongsa.domain;

import com.app.simbongsa.entity.file.FundingFile;
import com.app.simbongsa.entity.funding.FundingCreator;
import com.app.simbongsa.entity.funding.FundingGift;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.type.FundingCategoryType;
import com.app.simbongsa.type.RequestType;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Component
@Data
@NoArgsConstructor
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
    private Integer fundingCount;

    private MemberDTO memberDTO;
    private List<FileDTO> fileDTOs;
    private List<FundingGift> fundingGifts;

    public FundingDTO(List<FileDTO> fileDTOs) {
        this.fileDTOs = fileDTOs;
    }
//
//    @Builder
//    public FundingDTO(Long id, FundingCategoryType fundingCategory, String fundingTitle, String fundingShortTitle, String fundingSummary, int fundingTargetPrice, int fundingCurrentPrice, LocalDateTime fundingStartDate, LocalDateTime fundingEndDate, String fundingIntroduce, String fundingBudgetExplain, String fundingScheduleExplain, String fundingGiftExplain, RequestType fundingStatus, FundingCreator fundingCreator, MemberDTO memberDTO, List<FileDTO> fileDTOs, List<FundingGift> fundingGifts) {
//        this.id = id;
//        this.fundingCategory = fundingCategory;
//        this.fundingTitle = fundingTitle;
//        this.fundingShortTitle = fundingShortTitle;
//        this.fundingSummary = fundingSummary;
//        this.fundingTargetPrice = fundingTargetPrice;
//        this.fundingCurrentPrice = fundingCurrentPrice;
//        this.fundingPercent = (int)((double)(fundingCurrentPrice / fundingTargetPrice * 100));
//        this.fundingStartDate = fundingStartDate;
//        this.fundingEndDate = fundingEndDate;
//        this.fundingIntroduce = fundingIntroduce;
//        this.fundingBudgetExplain = fundingBudgetExplain;
//        this.fundingScheduleExplain = fundingScheduleExplain;
//        this.fundingGiftExplain = fundingGiftExplain;
//        this.fundingStatus = fundingStatus;
//
//
//    }

    @Builder
    public FundingDTO(Long id, FundingCategoryType fundingCategory, String fundingTitle, String fundingShortTitle, String fundingSummary, int fundingTargetPrice, int fundingCurrentPrice, LocalDateTime fundingStartDate, LocalDateTime fundingEndDate, String fundingIntroduce, String fundingBudgetExplain, String fundingScheduleExplain, String fundingGiftExplain, RequestType fundingStatus, FundingCreator fundingCreator/*, int fundingPercent*/) {
        this.id = id;
        this.fundingCategory = fundingCategory;
        this.fundingTitle = fundingTitle;
        this.fundingShortTitle = fundingShortTitle;
        this.fundingSummary = fundingSummary;
        this.fundingTargetPrice = fundingTargetPrice;
        this.fundingCurrentPrice = fundingCurrentPrice;
        this.fundingStartDate = fundingStartDate;
        this.fundingEndDate = fundingEndDate;
        this.fundingIntroduce = fundingIntroduce;
        this.fundingBudgetExplain = fundingBudgetExplain;
        this.fundingScheduleExplain = fundingScheduleExplain;
        this.fundingGiftExplain = fundingGiftExplain;
        this.fundingStatus = fundingStatus;
        this.fundingCreator = fundingCreator;
//        this.fundingPercent = (int)((double)(fundingCurrentPrice / fundingTargetPrice * 100));

    }
}


