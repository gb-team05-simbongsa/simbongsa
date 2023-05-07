package com.app.simbongsa.entity.support;

import com.app.simbongsa.entity.user.User;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(exclude = {"user", "supportRequest"})
@Table(name = "TBL_SUPPORT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Support {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private int supportPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUPPORT_REQUEST_ID")
    private SupportRequest supportRequest;

    /* 단위 테스트용 */
    public Support(int supportPrice, User user, SupportRequest supportRequest) {
        this.supportPrice = supportPrice;
        this.user = user;
        this.supportRequest = supportRequest;
    }
}
