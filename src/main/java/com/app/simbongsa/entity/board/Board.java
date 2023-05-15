package com.app.simbongsa.entity.board;

import com.app.simbongsa.audit.Period;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString
@Table(name = "TBL_BOARD")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Board extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String boardTitle;
    @NotNull private String boardContent;

    /* 단위테스트 위한 생성자 생성 */
    public Board(String boardTitle, String boardContent) {
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }
}
