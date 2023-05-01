package com.app.simbongsa.entity.rice;

import com.app.simbongsa.audit.Period;
import com.app.simbongsa.entity.user.User;
import com.app.simbongsa.type.RequestType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_RICE_EXCHANGE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RiceExchange extends Period {
    @Id @GeneratedValue
    private Long id;
    @NotNull private String riceExchangeBank;
    @NotNull private String riceExchangeAccountNumber;
    @NotNull private int riceExchangePrice;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("대기")
    @NotNull private RequestType riceExchangeStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Builder
    public RiceExchange(String riceExchangeBank, String riceExchangeAccountNumber, int riceExchangePrice, User user) {
        this.riceExchangeBank = riceExchangeBank;
        this.riceExchangeAccountNumber = riceExchangeAccountNumber;
        this.riceExchangePrice = riceExchangePrice;
        this.user = user;
    }
}
