package com.app.simbongsa.entity.board;

import com.app.simbongsa.audit.Period;
import com.app.simbongsa.entity.member.Member;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(exclude = {"member", "freeBoard"})
@Table(name = "TBL_FREE_BOARD_REPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeBoardReply extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String freeBoardReplyContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FREE_BOARD_ID")
    private FreeBoard freeBoard;



    // 단위 테스트용 생성자
    public FreeBoardReply(String freeBoardReplyContent, Member member, FreeBoard freeBoard) {
        this.freeBoardReplyContent = freeBoardReplyContent;
        this.member = member;
        this.freeBoard = freeBoard;
    }

}
