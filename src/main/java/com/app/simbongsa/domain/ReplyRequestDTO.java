package com.app.simbongsa.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class ReplyRequestDTO {
    private Long memberId;
    private Long boardId;
    private String replyContent;
}
