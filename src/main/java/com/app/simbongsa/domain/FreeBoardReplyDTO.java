package com.app.simbongsa.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class FreeBoardReplyDTO {
    private Long id;
    private MemberDTO memberDTO;
    private String freeBoardReplyContent;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private FreeBoardDTO freeBoardDTO;

    @Builder
    public FreeBoardReplyDTO(Long id, MemberDTO memberDTO, String freeBoardReplyContent, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.memberDTO = memberDTO;
        this.freeBoardReplyContent = freeBoardReplyContent;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
