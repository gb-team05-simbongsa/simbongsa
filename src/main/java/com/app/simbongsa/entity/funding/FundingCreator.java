package com.app.simbongsa.entity.funding;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@Getter @ToString
@NoArgsConstructor
public class FundingCreator {
    @NotNull private String fundingCreatorNickname;
    @NotNull private String fundingCreatorIntroduce;
    private String fundingCreatorBank;
    private String fundingCreatorAccountNumber;

//    단위테스트용 생성

    @Builder
    public FundingCreator(String fundingCreatorNickname, String fundingCreatorIntroduce, String fundingCreatorBank, String fundingCreatorAccountNumber) {
        this.fundingCreatorNickname = fundingCreatorNickname;
        this.fundingCreatorIntroduce = fundingCreatorIntroduce;
        this.fundingCreatorBank = fundingCreatorBank;
        this.fundingCreatorAccountNumber = fundingCreatorAccountNumber;
    }

    public void setFundingCreatorNickname(String fundingCreatorNickname) {
        this.fundingCreatorNickname = fundingCreatorNickname;
    }

    public void setFundingCreatorIntroduce(String fundingCreatorIntroduce) {
        this.fundingCreatorIntroduce = fundingCreatorIntroduce;
    }

    public void setFundingCreatorBank(String fundingCreatorBank) {
        this.fundingCreatorBank = fundingCreatorBank;
    }

    public void setFundingCreatorAccountNumber(String fundingCreatorAccountNumber) {
        this.fundingCreatorAccountNumber = fundingCreatorAccountNumber;
    }
}
