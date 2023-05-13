package com.app.simbongsa.entity.file;

import com.app.simbongsa.entity.support.SupportRequest;
import com.app.simbongsa.type.FileRepresentationalType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true, exclude = "supportRequest")
@Table(name = "TBL_SUPPORT_REQUEST_FILE")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SupportRequestFile extends File {

    @ManyToOne(fetch = FetchType.LAZY)
    private SupportRequest supportRequest;

    public SupportRequestFile(SupportRequest supportRequest) {
        this.supportRequest = supportRequest;
    }

    @Builder
    public SupportRequestFile(String fileName, String fileUuid, String filePath, FileRepresentationalType fileRepresentationalType, SupportRequest supportRequest) {
        super(fileName, fileUuid, filePath, fileRepresentationalType);
        this.supportRequest = supportRequest;
    }
}
