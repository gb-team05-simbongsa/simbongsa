package com.app.simbongsa.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FundingPaymentDTO {
    private Long id;
    private int fundingPaymentPrice;
    private LocalDateTime fundingPaymentDate;
    private MemberDTO memberDTO;
    private FundingDTO fundingDTO;

    @Builder
    public FundingPaymentDTO(Long id, int fundingPaymentPrice, LocalDateTime fundingPaymentDate, MemberDTO memberDTO, FundingDTO fundingDTO) {
        this.id = id;
        this.fundingPaymentPrice = fundingPaymentPrice;
        this.fundingPaymentDate = fundingPaymentDate;
        this.memberDTO = memberDTO;
        this.fundingDTO = fundingDTO;
    }
}
