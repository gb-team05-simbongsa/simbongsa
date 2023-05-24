package com.app.simbongsa.entity.inquiry;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(exclude = "inquiry")
@Table(name = "TBL_ANSWER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String answerTitle;
    @NotNull private String answerContent;

    @OneToOne(fetch = FetchType.LAZY)
    private Inquiry inquiry;

    @Builder
    public Answer(Long id, String answerTitle, String answerContent, Inquiry inquiry) {
        this.id = id;
        this.answerTitle = answerTitle;
        this.answerContent = answerContent;
        this.inquiry = inquiry;
    }
}
