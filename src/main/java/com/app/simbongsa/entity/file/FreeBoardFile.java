package com.app.simbongsa.entity.file;

import com.app.simbongsa.entity.board.Board;
import com.app.simbongsa.entity.board.FreeBoard;
import com.app.simbongsa.type.FileRepresentationalType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    public FreeBoardFile(String fileName, String fileUuid, String filePath, FileRepresentationalType fileRepresentationalType, FreeBoard freeBoard) {
        super(fileName, fileUuid, filePath, fileRepresentationalType);
        this.freeBoard = freeBoard;
    }
}
