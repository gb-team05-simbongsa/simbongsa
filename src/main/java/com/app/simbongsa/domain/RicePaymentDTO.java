package com.app.simbongsa.domain;

import com.app.simbongsa.type.RicePaymentType;
import lombok.Builder;
import lombok.Data;

@Data
public class RicePaymentDTO {
    private Long id;
    private int ricePaymentUsed;
    private RicePaymentType ricePaymentStatus;
    private String ricePaymentExchangeBank;
    private String ricePaymentExchangeAccount;
    private MemberDTO memberDTO;

    @Builder
    public RicePaymentDTO(Long id, int ricePaymentUsed, RicePaymentType ricePaymentStatus, String ricePaymentExchangeBank, String ricePaymentExchangeAccount, MemberDTO memberDTO) {
        this.id = id;
        this.ricePaymentUsed = ricePaymentUsed;
        this.ricePaymentStatus = ricePaymentStatus;
        this.ricePaymentExchangeBank = ricePaymentExchangeBank;
        this.ricePaymentExchangeAccount = ricePaymentExchangeAccount;
        this.memberDTO = memberDTO;
    }
}
