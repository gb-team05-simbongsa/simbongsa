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
public class Board extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String BoardTitle;
    @NotNull private String BoardContent;

    /* 단위테스트 위한 생성자 생성 */
    public Board(String boardTitle, String boardContent) {
        BoardTitle = boardTitle;
        BoardContent = boardContent;
    }
}
