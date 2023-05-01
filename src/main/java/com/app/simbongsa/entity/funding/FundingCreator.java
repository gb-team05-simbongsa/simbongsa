package com.app.simbongsa.entity.funding;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@Getter @ToString
public class FundingCreator {
    @NotNull private String fundingCreatorNickname;
    @NotNull private String fundingCreatorIntroduce;
    private String fundingCreatorBank;
    private String fundingCreatorAccountNumber;
}
