package com.app.simbongsa.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoticeDTO {
    private Long id;
    private String noticeTitle;
    private String noticeContent;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
//    private Long noticeCount;

    @Builder
    public NoticeDTO(Long id, String noticeTitle, String noticeContent, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
//
//    @Builder
//    public NoticeDTO(Long id, String noticeTitle, String noticeContent, LocalDateTime createdDate, LocalDateTime updatedDate, Long noticeCount) {
//        this.id = id;
//        this.noticeTitle = noticeTitle;
//        this.noticeContent = noticeContent;
//        this.createdDate = createdDate;
//        this.updatedDate = updatedDate;
//        this.noticeCount = noticeCount;
//    }
}
