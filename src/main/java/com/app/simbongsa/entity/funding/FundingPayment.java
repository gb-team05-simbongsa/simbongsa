package com.app.simbongsa.entity.funding;

import com.app.simbongsa.entity.user.User;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_FUNDING_PAYMENT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FundingPayment {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private int fundingPaymentPrice;
    @NotNull private LocalDateTime fundingPaymentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FUNDING_ID")
    private Funding funding;

    @Builder
    public FundingPayment(int fundingPaymentPrice, LocalDateTime fundingPaymentDate, User user, Funding funding) {
        this.fundingPaymentPrice = fundingPaymentPrice;
        this.fundingPaymentDate = fundingPaymentDate;
        this.user = user;
        this.funding = funding;
    }
}
