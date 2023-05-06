package com.app.simbongsa.entity.funding;

import com.sun.istack.NotNull;
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

    public FundingCreator(String fundingCreatorNickname, String fundingCreatorIntroduce, String fundingCreatorBank, String fundingCreatorAccountNumber) {
        this.fundingCreatorNickname = fundingCreatorNickname;
        this.fundingCreatorIntroduce = fundingCreatorIntroduce;
        this.fundingCreatorBank = fundingCreatorBank;
        this.fundingCreatorAccountNumber = fundingCreatorAccountNumber;
    }
}
