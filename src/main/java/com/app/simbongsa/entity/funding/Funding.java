package com.app.simbongsa.entity.funding;

import com.app.simbongsa.entity.file.FundingFile;
import com.app.simbongsa.entity.user.User;
import com.app.simbongsa.type.FundingCategoryType;
import com.app.simbongsa.type.RequestType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @ToString(exclude = "user")
@Table(name = "TBL_FUNDING")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class Funding {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @Enumerated(EnumType.STRING)
    @NotNull private FundingCategoryType fundingCategory;
    @NotNull private String fundingTitle;
    @NotNull private String fundingShortTitle;
    @NotNull private String fundingSummary;
    @NotNull private int fundingTargetPrice;
    @NotNull private int fundingCurrentPrice;
    @NotNull private LocalDateTime fundingStartDate;
    @NotNull private LocalDateTime fundingEndDate;
    @NotNull private String fundingIntroduce;
    @NotNull private String fundingBudgetExplain;
    @NotNull private String fundingScheduleExplain;
    @NotNull private String fundingGiftExplain;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'대기'")
    @NotNull private RequestType fundingStatus;
    @Embedded
    @NotNull private FundingCreator fundingCreator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funding")
    private List<FundingFile> fundingFile;

    public Funding(User user) {
        this.user = user;
    }

    // 테스트용 생성자
    public Funding(FundingCategoryType fundingCategory, String fundingTitle, String fundingShortTitle, String fundingSummary, int fundingTargetPrice, int fundingCurrentPrice, LocalDateTime fundingStartDate, LocalDateTime fundingEndDate, String fundingIntroduce, String fundingBudgetExplain, String fundingScheduleExplain, String fundingGiftExplain, FundingCreator fundingCreator, User user) {
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
        this.fundingCreator = fundingCreator;
        this.user = user;



    }
}
