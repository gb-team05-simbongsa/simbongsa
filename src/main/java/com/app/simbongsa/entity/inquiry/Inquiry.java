package com.app.simbongsa.entity.inquiry;

import com.app.simbongsa.audit.Period;
import com.app.simbongsa.entity.user.User;
import com.app.simbongsa.type.InquiryType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter @ToString
@Table(name = "TBL_INQUIRY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inquiry extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String inquiryTitle;
    @NotNull private String inquiryContent;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'답변대기'")
    @NotNull private InquiryType inquiryStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
}
