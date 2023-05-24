package com.app.simbongsa.entity.funding;

import com.app.simbongsa.entity.member.Member;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @ToString(exclude = "funding")
@Table(name = "TBL_FUNDING_PAYMENT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FundingPayment {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private int fundingPaymentPrice;
    @NotNull private LocalDateTime fundingPaymentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FUNDING_ID")
    private Funding funding;
}
