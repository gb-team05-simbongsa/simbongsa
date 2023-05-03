package com.app.simbongsa.entity.file;

import com.app.simbongsa.audit.Period;
import com.app.simbongsa.entity.board.Board;
import com.app.simbongsa.type.FileRepresentationalType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString
@Table(name = "TBL_BOARD_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardFile {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String fileName;
    private String fileUuid;
    private String filePath;

    //    대표 이미지 검사
    @Enumerated(EnumType.STRING)
    @NotNull private FileRepresentationalType fileRepresentationalType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

}
