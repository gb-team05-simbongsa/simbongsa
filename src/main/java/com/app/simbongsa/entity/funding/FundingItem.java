package com.app.simbongsa.entity.funding;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_FUNDING_ITEM")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FundingItem {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String itemTitle;
    @NotNull private String itemContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FUNDING_ID")
    private Funding funding;
}
