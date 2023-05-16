package com.app.simbongsa.entity.file;

import com.app.simbongsa.entity.board.FreeBoard;
import com.app.simbongsa.type.FileRepresentationalType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true, exclude = "freeBoard")
@Table(name = "TBL_FREE_BOARD_FILE")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeBoardFile extends File {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FREE_BOARD_ID")
    private FreeBoard freeBoard;

    @Builder
    public FreeBoardFile(Long id, String fileName, String fileUuid, String filePath, FileRepresentationalType fileRepresentationalType, FreeBoard freeBoard) {
        super(id, fileName, fileUuid, filePath, fileRepresentationalType);
        this.freeBoard = freeBoard;
    }


}
