package com.app.simbongsa.entity.funding;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString(exclude = {"fundingItem", "fundingGift"})
@Table(name = "TBL_FUNDING_GIFT_ITEM")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FundingGiftItem {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private  FundingItem fundingItem;

    @ManyToOne(fetch = FetchType.LAZY)
    private FundingGift fundingGift;


    public FundingGiftItem(FundingItem fundingItem, FundingGift fundingGift) {
        this.fundingItem = fundingItem;
        this.fundingGift = fundingGift;
    }

//    public static void setFundingItem(FundingItem fundingItem) {
//        this.fundingItem = fundingItem;
//    }
}
