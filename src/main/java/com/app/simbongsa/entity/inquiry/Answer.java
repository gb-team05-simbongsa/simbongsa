package com.app.simbongsa.entity.inquiry;

import com.app.simbongsa.entity.funding.Funding;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter @ToString
@Table(name = "TBL_ANSWER")
public class Answer {
    @Id @GeneratedValue
    private Long id;
    @NotNull private String answerTitle;
    @NotNull private String answerContent;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INQUIRY_ID")
    private Inquiry inquiry;
}
