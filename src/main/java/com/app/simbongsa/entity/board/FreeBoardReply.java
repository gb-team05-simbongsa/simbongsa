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
    @JoinColumn(name = "FREEBOARD_ID")
    private FreeBoard freeBoard;


    public FreeBoardReply(String replyContent, FreeBoard freeBoard, Member member) {
        super(replyContent);
        this.freeBoard = freeBoard;
        this.member = member;
    }

    @Builder
    public FreeBoardReply(Long id, String replyContent, FreeBoard freeBoard, Member member){
        super(id, replyContent);
        this.freeBoard = freeBoard;
        this.member = member;
    }


    public void setFreeBoard(FreeBoard freeBoard) {
        this.freeBoard = freeBoard;
    }

    public void setFreeBoardReplyContent(String replyContent) {
        super.setReplyContent(replyContent);
    }


}
