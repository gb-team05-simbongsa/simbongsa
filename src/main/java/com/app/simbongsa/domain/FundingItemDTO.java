package com.app.simbongsa.domain;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class FundingItemDTO {
    private Long id;
    private String itemTitle;
    private String itemType;
     private String itemContent;


    @Builder
    public FundingItemDTO(Long id, String itemTitle, String itemType, String itemContent) {
        this.id = id;
        this.itemTitle = itemTitle;
        this.itemType = itemType;
        this.itemContent = itemContent;
    }
}
