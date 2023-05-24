package com.app.simbongsa.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReviewReplyDTO {
    private Long id;
    private MemberDTO memberDTO;
    private String reviewReplyContent;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private ReviewDTO reviewDTO;

    @Builder

    public ReviewReplyDTO(Long id, MemberDTO memberDTO, String reviewReplyContent, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.memberDTO = memberDTO;
        this.reviewReplyContent = reviewReplyContent;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
