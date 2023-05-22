package com.app.simbongsa.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Builder
@Component
@NoArgsConstructor
public class FreeBoardReplyDTO {
    private Long id;
    private MemberDTO memberDTO;
    private String replyContent;
    private Long boardId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @QueryProjection
    public FreeBoardReplyDTO(Long id, MemberDTO memberDTO, String replyContent, Long boardId, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.memberDTO = memberDTO;
        this.replyContent = replyContent;
        this.boardId = boardId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
