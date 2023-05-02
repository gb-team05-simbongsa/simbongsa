package com.app.simbongsa.entity.rice;

import com.app.simbongsa.entity.user.User;
import com.app.simbongsa.type.RicePaymentType;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_RICE_PAYMENT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RicePayment {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private int ricePaymentUsed;
    @CreatedDate @Column(updatable = false)
    @NotNull private LocalDateTime ricePaymentTime;
    @Enumerated(EnumType.STRING)
    @NotNull private RicePaymentType ricePaymentStatus;
    private String ricePaymentExchangeBank;
    private String ricePaymentExchangeAccountNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;
}
