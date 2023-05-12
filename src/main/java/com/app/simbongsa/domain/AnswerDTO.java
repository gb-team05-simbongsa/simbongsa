package com.app.simbongsa.domain;

import lombok.Data;

@Data
public class AnswerDTO {
    private Long id;
    private String answerTitle;
    private String answerContent;
    private InquiryDTO inquiryDTO;

//    private Inquiry inquiry;
}
