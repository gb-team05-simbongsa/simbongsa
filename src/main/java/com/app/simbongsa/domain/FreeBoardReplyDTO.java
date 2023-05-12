package com.app.simbongsa.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
//@Component
public class FreeBoardReplyDTO {
    private Long id;
    private String freeBoardReplyContent;

    @Builder
    public FreeBoardReplyDTO(Long id, String freeBoardReplyContent) {
        this.id = id;
        this.freeBoardReplyContent = freeBoardReplyContent;
    }
}
