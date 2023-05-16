package com.app.simbongsa.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class ReplyDTO {
    private Long id;
    private String replyContent;
    private LocalDateTime registerDate;
    private MemberDTO memberDTO;
    private FreeBoardDTO freeBoardDTO;
    private ReviewDTO reviewDTO;

    @Builder
    public ReplyDTO(Long id, String replyContent, LocalDateTime registerDate, MemberDTO memberDTO) {
        this.id = id;
        this.replyContent = replyContent;
        this.registerDate = registerDate;
        this.memberDTO = memberDTO;
    }

    @Builder
    public ReplyDTO(Long id, String replyContent, LocalDateTime registerDate, MemberDTO memberDTO, FreeBoardDTO freeBoardDTO) {
        this.id = id;
        this.replyContent = replyContent;
        this.registerDate = registerDate;
        this.memberDTO = memberDTO;
        this.freeBoardDTO = freeBoardDTO;
    }

    @Builder
    public ReplyDTO(Long id, String replyContent, LocalDateTime registerDate, MemberDTO memberDTO, ReviewDTO reviewDTO) {
        this.id = id;
        this.replyContent = replyContent;
        this.registerDate = registerDate;
        this.memberDTO = memberDTO;
        this.reviewDTO = reviewDTO;
    }
}
