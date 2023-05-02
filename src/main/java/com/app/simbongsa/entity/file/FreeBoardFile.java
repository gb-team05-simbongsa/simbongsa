package com.app.simbongsa.entity.file;

import com.app.simbongsa.type.FileRepresentationalType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_FREE_BOARD_FILE")
@PrimaryKeyJoinColumn(name = "FREE_BOARD_ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeBoardFile extends File {
//    대표 이미지 검사
    @Enumerated(EnumType.STRING)
    @NotNull private FileRepresentationalType fileRepresentationalType;
}
