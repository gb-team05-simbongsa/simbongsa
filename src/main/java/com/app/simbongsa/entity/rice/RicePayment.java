package com.app.simbongsa.entity.rice;

import com.app.simbongsa.audit.Period;
import com.app.simbongsa.entity.user.User;
import com.app.simbongsa.type.RicePaymentType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @ToString(exclude = "user")
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
    @JoinColumn(name = "USER_ID")
    private User user;

//    더미 데이터용 생성자
    public RicePayment(int ricePaymentUsed, RicePaymentType ricePaymentStatus, String ricePaymentExchangeBank, String ricePaymentExchangeAccount, User user) {
        this.ricePaymentUsed = ricePaymentUsed;
        this.ricePaymentStatus = ricePaymentStatus;
        this.ricePaymentExchangeBank = ricePaymentExchangeBank;
        this.ricePaymentExchangeAccount = ricePaymentExchangeAccount;
        this.user = user;
    }
}
