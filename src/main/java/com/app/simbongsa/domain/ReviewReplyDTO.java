package com.app.simbongsa.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
@NoArgsConstructor
public class ReviewReplyDTO {
    private Long id;
    private String freeBoardReplyContent;
    private LocalDateTime registerDate;

    private MemberDTO memberDTO;
    private ReviewDTO reviewDTO;

    @Builder
    public ReviewReplyDTO(Long id, String freeBoardReplyContent, LocalDateTime registerDate, MemberDTO memberDTO) {
        this.id = id;
        this.freeBoardReplyContent = freeBoardReplyContent;
        this.registerDate = registerDate;
        this.memberDTO = memberDTO;
    }

    @Builder
    public ReviewReplyDTO(Long id, String freeBoardReplyContent, LocalDateTime registerDate, MemberDTO memberDTO, ReviewDTO reviewDTO) {
        this.id = id;
        this.freeBoardReplyContent = freeBoardReplyContent;
        this.registerDate = registerDate;
        this.memberDTO = memberDTO;
        this.reviewDTO = reviewDTO;
    }
}
