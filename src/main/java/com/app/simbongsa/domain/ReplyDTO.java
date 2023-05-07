package com.app.simbongsa.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @ToString
public class ReplyDTO {
    private Long id;
    private String replyContent;
    private LocalDateTime registerDate;

    private String userNickname;
    private String fileName;
    private String fileUuid;
    private String filePath;

    @QueryProjection
    public ReplyDTO(Long id, String replyContent, LocalDateTime registerDate, Long userId, String userNickname, String fileName, String fileUuid, String filePath) {
        this.id = id;
        this.replyContent = replyContent;
        this.registerDate = registerDate;
        this.userNickname = userNickname;
        this.fileName = fileName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
    }
}
