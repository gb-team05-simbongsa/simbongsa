package com.app.simbongsa.domain;

import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.entity.support.SupportRequest;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@NoArgsConstructor
public class SupportDTO {
    private Long id;
    private int supportPrice;
    private MemberDTO memberDTO;
    private SupportRequestDTO supportRequestDTO;

    @Builder
    public SupportDTO(Long id, int supportPrice, MemberDTO memberDTO, SupportRequestDTO supportRequestDTO) {
        this.id = id;
        this.supportPrice = supportPrice;
        this.memberDTO = memberDTO;
        this.supportRequestDTO = supportRequestDTO;
    }
}
