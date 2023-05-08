package com.app.simbongsa.entity.support;

import com.app.simbongsa.audit.Period;
import com.app.simbongsa.entity.file.QSupportRequestFile;
import com.app.simbongsa.entity.file.SupportRequestFile;
import com.app.simbongsa.entity.user.User;
import com.app.simbongsa.type.RequestType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @ToString(exclude = "user")
@Table(name = "TBL_SUPPORT_REQUEST")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class SupportRequest extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String supportRequestTitle;
    @NotNull private String supportRequestContent;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'대기'")
    @NotNull private RequestType supportRequestStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supportRequest")
    private List<SupportRequestFile> supportRequestFiles;

    /* 단위 테스트용 생성자 생성 */

    public SupportRequest(String supportRequestTitle, String supportRequestContent, RequestType supportRequestStatus, User user) {
        this.supportRequestTitle = supportRequestTitle;
        this.supportRequestContent = supportRequestContent;
        this.supportRequestStatus = supportRequestStatus;
        this.user = user;
    }
}
