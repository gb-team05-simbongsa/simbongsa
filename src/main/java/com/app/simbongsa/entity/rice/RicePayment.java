package com.app.simbongsa.entity.rice;

import com.app.simbongsa.audit.Period;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.type.RicePaymentType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter @ToString(exclude = "member")
@Table(name = "TBL_RICE_PAYMENT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class RicePayment extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private int ricePaymentUsed;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'상태미지정'")
    @NotNull private RicePaymentType ricePaymentStatus;
    private String ricePaymentExchangeBank;
    private String ricePaymentExchangeAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

//    더미 데이터용 생성자
    public RicePayment(int ricePaymentUsed, RicePaymentType ricePaymentStatus, String ricePaymentExchangeBank, String ricePaymentExchangeAccount, Member member) {
        this.ricePaymentUsed = ricePaymentUsed;
        this.ricePaymentStatus = ricePaymentStatus;
        this.ricePaymentExchangeBank = ricePaymentExchangeBank;
        this.ricePaymentExchangeAccount = ricePaymentExchangeAccount;
        this.member = member;
    }
}
