package com.app.simbongsa.entity.inquiry;

import com.app.simbongsa.audit.Period;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter @ToString
@Table(name = "TBL_NOTICE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String noticeTitle;
    @NotNull private String noticeContent;

    public Notice(String noticeTitle, String noticeContent) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

//    서비스에 넣기
    public void updateNotice(String noticeTitle, String noticeContent) {
        setNoticeTitle(noticeTitle);
        setNoticeContent(noticeContent);
    }
}
