package com.app.simbongsa.entity.funding;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @ToString(exclude = {"funding", "fundingGiftItems"})
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
    private Funding funding;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fundingGift", cascade = CascadeType.REMOVE)
    private List<FundingGiftItem> fundingGiftItems;

}
