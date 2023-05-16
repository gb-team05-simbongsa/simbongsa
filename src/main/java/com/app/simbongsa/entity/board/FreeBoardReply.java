package com.app.simbongsa.entity.board;

import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.entity.reply.Reply;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true,exclude = "freeBoard")
@Table(name = "TBL_FREE_BOARD_REPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeBoardReply extends Reply {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private FreeBoard freeBoard;



    // 단위 테스트용 생성자
    public FreeBoardReply(String replyContent, Member member, FreeBoard freeBoard) {
        super(replyContent);
        this.member = member;
        this.freeBoard = freeBoard;
    }

    public void setFreeBoardReplyContent(String replyContent) {
        super.setReplyContent(replyContent);
    }


    @Builder
    public FreeBoardReply(Long id, String replyContent, FreeBoard freeBoard, Member member) {
        super(id, replyContent);
        this.freeBoard = freeBoard;
        this.member = member;
    }
}
