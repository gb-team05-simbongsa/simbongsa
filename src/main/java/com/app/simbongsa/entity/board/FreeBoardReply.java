package com.app.simbongsa.entity.board;

import com.app.simbongsa.audit.Period;
import com.app.simbongsa.entity.user.User;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(exclude = {"user", "freeBoard"})
@Table(name = "TBL_FREE_BOARD_REPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeBoardReply extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String freeBoardReplyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "FREE_BOARD_ID")
    private FreeBoard freeBoard;



    // 단위 테스트용 생성자
    public FreeBoardReply(String freeBoardReplyContent, User user, FreeBoard freeBoard) {
        this.freeBoardReplyContent = freeBoardReplyContent;
        this.user = user;
        this.freeBoard = freeBoard;
    }
}
