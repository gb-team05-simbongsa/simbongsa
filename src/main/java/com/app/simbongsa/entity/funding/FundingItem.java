package com.app.simbongsa.entity.funding;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @ToString(exclude = "fundingGiftItems")
@Table(name = "TBL_FUNDING_ITEM")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FundingItem {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String itemTitle;
    @NotNull private String itemContent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fundingItem", cascade = CascadeType.REMOVE)
    private List<FundingGiftItem> fundingGiftItems;


    //단위테스트용 생성자
    public FundingItem(String itemTitle, String itemContent) {
        this.itemTitle = itemTitle;
        this.itemContent = itemContent;

    }
}
