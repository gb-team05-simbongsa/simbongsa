package com.app.simbongsa.domain;

import com.app.simbongsa.type.InquiryType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class InquiryDTO {
    private Long id;
    private String inquiryTitle;
    private String inquiryContent;
    private InquiryType inquiryStatus;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private AnswerDTO answerDTO;
    private MemberDTO memberDTO;

    @Builder
    public InquiryDTO(Long id, String inquiryTitle, String inquiryContent, InquiryType inquiryStatus, LocalDateTime createdDate, LocalDateTime updatedDate, AnswerDTO answerDTO, MemberDTO memberDTO) {
        this.id = id;
        this.inquiryTitle = inquiryTitle;
        this.inquiryContent = inquiryContent;
        this.inquiryStatus = inquiryStatus;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.answerDTO = answerDTO;
        this.memberDTO = memberDTO;
    }
}
