package com.app.simbongsa.entity.file;

import com.app.simbongsa.type.FileRepresentationalType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_FUNDING_FILE")
@PrimaryKeyJoinColumn(name = "FUNDING_ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FundingFile extends File {
//    대표 이미지 검사
    @Enumerated(EnumType.STRING)
    @NotNull private FileRepresentationalType fileRepresentationalType;
}
