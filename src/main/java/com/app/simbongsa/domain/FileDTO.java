package com.app.simbongsa.domain;

import com.app.simbongsa.type.FileRepresentationalType;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
//@Component
public class FileDTO {
    private Long id;
    private String fileName;
    private String fileUuid;
    private String filePath;
    private FileRepresentationalType fileRepresentationalType;

    @Builder
    public FileDTO(Long id, String fileName, String fileUuid, String filePath) {
        this.id = id;
        this.fileName = fileName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
    }
}
