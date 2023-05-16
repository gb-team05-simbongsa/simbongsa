package com.app.simbongsa.entity.file;

import com.app.simbongsa.type.FileRepresentationalType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString
@Table(name = "TBL_FILE")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String fileName;
    private String fileUuid;
    private String filePath;

    //    대표 이미지 검사
    @Enumerated(EnumType.STRING)
    @NotNull private FileRepresentationalType fileRepresentationalType;

    public File(Long id, String fileName, String fileUuid, String filePath, FileRepresentationalType fileRepresentationalType) {
        this.id = id;
        this.fileName = fileName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.fileRepresentationalType = fileRepresentationalType;
    }


}
