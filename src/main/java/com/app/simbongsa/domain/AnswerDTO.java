package com.app.simbongsa.domain;

import lombok.Builder;
import lombok.Data;

@Data
public class AnswerDTO {
    private Long id;
    private String answerTitle;
    private String answerContent;
    private InquiryDTO inquiryDTO;

    @Builder
    public AnswerDTO(Long id, String answerTitle, String answerContent) {
        this.id = id;
        this.answerTitle = answerTitle;
        this.answerContent = answerContent;
    }


    //    private Inquiry inquiry;
}
