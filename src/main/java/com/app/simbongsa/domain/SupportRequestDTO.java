package com.app.simbongsa.domain;

import com.app.simbongsa.entity.file.SupportRequestFile;
import com.app.simbongsa.entity.support.Support;
import com.app.simbongsa.type.RequestType;
import lombok.Builder;
import lombok.Data;

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
    private List<Support> supports;
    private List<SupportRequestFile> supportRequestFiles;

    @Builder
    public SupportRequestDTO(Long id, String supportRequestTitle, String supportRequestContent, RequestType supportRequestStatus, LocalDateTime createdDate, LocalDateTime updatedDate, MemberDTO memberDTO, List<Support> supports, List<SupportRequestFile> supportRequestFiles) {
        this.id = id;
        this.supportRequestTitle = supportRequestTitle;
        this.supportRequestContent = supportRequestContent;
        this.supportRequestStatus = supportRequestStatus;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.memberDTO = memberDTO;
        this.supports = supports;
        this.supportRequestFiles = supportRequestFiles;
    }
}
