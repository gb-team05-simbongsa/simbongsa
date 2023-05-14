package com.app.simbongsa.summernote;

import com.app.simbongsa.entity.file.SupportRequestFile;
import com.app.simbongsa.entity.support.SupportRequest;
import com.app.simbongsa.type.FileRepresentationalType;
import lombok.Builder;
import lombok.Data;

@Data
public class SupportRequestFileDTO {
    private String fileName;
    private String fileUuid;
    private String filePath;
    private FileRepresentationalType fileRepresentationalType;
    private SupportRequest supportRequest;

    // 생성자, 게터, 세터, toString 등 필요한 메서드 작성
    // ...
    @Builder
    public SupportRequestFileDTO(Long id, String fileName, String fileUuid, String filePath, FileRepresentationalType fileRepresentationalType, SupportRequest supportRequest) {
        this.fileName = fileName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.fileRepresentationalType = fileRepresentationalType;
        this.supportRequest = supportRequest;
    }

    public static SupportRequestFileDTO toDTO(SupportRequestFile supportRequestFile) {
        return SupportRequestFileDTO.builder()
                .fileName(supportRequestFile.getFileName())
                .fileUuid(supportRequestFile.getFileUuid())
                .filePath(supportRequestFile.getFilePath())
                .fileRepresentationalType(supportRequestFile.getFileRepresentationalType())
                .supportRequest(supportRequestFile.getSupportRequest())
                .build();
    }

    public SupportRequestFile toEntity() {
        return SupportRequestFile.builder()
                .fileName(this.fileName)
                .fileUuid(this.fileUuid)
                .filePath(this.filePath)
                .fileRepresentationalType(this.fileRepresentationalType)
                .supportRequest(this.supportRequest)
                .build();
    }
}