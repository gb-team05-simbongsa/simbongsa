package com.app.simbongsa.entity.support;

import com.app.simbongsa.entity.member.Member;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(exclude = {"member", "supportRequest"})
@Table(name = "TBL_SUPPORT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Support {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private int supportPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private SupportRequest supportRequest;

    /* 단위 테스트용 */
    public Support(int supportPrice, Member member, SupportRequest supportRequest) {
        this.supportPrice = supportPrice;
        this.member = member;
        this.supportRequest = supportRequest;
    }
}
