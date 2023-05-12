package com.app.simbongsa.domain;

import com.app.simbongsa.type.InquiryType;
import lombok.Builder;
import lombok.Data;


@Data
public class InquiryDTO {
    private Long id;
    private String inquiryTitle;
    private String inquiryContent;
    private InquiryType inquiryStatus;
    private MemberDTO memberDTO;

    @Builder
    public InquiryDTO(Long id, String inquiryTitle, String inquiryContent, InquiryType inquiryStatus, MemberDTO memberDTO) {
        this.id = id;
        this.inquiryTitle = inquiryTitle;
        this.inquiryContent = inquiryContent;
        this.inquiryStatus = inquiryStatus;
        this.memberDTO = memberDTO;
    }

    //    private Member member;
}
