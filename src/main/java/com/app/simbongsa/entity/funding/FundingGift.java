package com.app.simbongsa.entity.funding;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(exclude = "funding")
@Table(name = "TBL_FUNDING_GIFT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FundingGift {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String fundingGiftExplain;
    @NotNull private int fundingGiftAmount;
    @NotNull private int fundingGiftPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FUNDING_ID")
    private Funding funding;
}
