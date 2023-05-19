package com.app.simbongsa.domain;

import com.app.simbongsa.entity.file.SupportRequestFile;
import com.app.simbongsa.entity.support.Support;
import com.app.simbongsa.summernote.SupportRequestFileDTO;
import com.app.simbongsa.type.RequestType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SupportRequestDTO {
    private Long id;
    private String supportRequestTitle;
    private String supportRequestContent;
    private RequestType supportRequestStatus;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private MemberDTO memberDTO;
    private List<SupportDTO> supportDTOS;
    private List<FileDTO> fileDTOS;
    private int totalSupportPrice;

    @Builder
    public SupportRequestDTO(Long id, String supportRequestTitle, String supportRequestContent, RequestType supportRequestStatus, LocalDateTime createdDate, LocalDateTime updatedDate, MemberDTO memberDTO, List<SupportDTO> supportDTOS, List<FileDTO> fileDTOS) {
        this.id = id;
        this.supportRequestTitle = supportRequestTitle;
        this.supportRequestContent = supportRequestContent;
        this.supportRequestStatus = supportRequestStatus;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.memberDTO = memberDTO;
        this.supportDTOS = supportDTOS;
        this.fileDTOS = fileDTOS;
        this.totalSupportPrice = calculateTotalSupportPrice();
    }

    private int calculateTotalSupportPrice() {
        if (supportDTOS == null) {
            return 0;
        }

        int total = 0;
        for (SupportDTO support : supportDTOS) {
            total += support.getSupportPrice();
        }
        return total;
    }

}
