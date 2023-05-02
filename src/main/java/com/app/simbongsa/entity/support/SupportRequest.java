package com.app.simbongsa.entity.support;

import com.app.simbongsa.audit.Period;
import com.app.simbongsa.entity.user.User;
import com.app.simbongsa.type.RequestType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_SUPPORT_REQUEST")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SupportRequest extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String supportRequestTitle;
    @NotNull private String supportRequestContent;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("대기")
    @NotNull private RequestType supportRequestStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
}
