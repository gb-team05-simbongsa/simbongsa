package com.app.simbongsa.domain;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
public class FundingItemDTO {
    private Long id;
    private String itemTitle;
     private String itemContent;


    @Builder
    public FundingItemDTO(Long id, String itemTitle, String itemContent) {
        this.id = id;
        this.itemTitle = itemTitle;
        this.itemContent = itemContent;
    }
}
