package com.app.simbongsa.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
@Builder
public class PageDTO {
    private int totalPage;
    private Long totalElements;
    private int currentNumber;
    private boolean hasNext;
    private boolean hasPrevious;
    private int startPage;
    private int endPage;
    private boolean isLast;
}
