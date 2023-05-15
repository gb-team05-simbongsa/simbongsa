package com.app.simbongsa.entity.board;

import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.entity.reply.Reply;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true,exclude = "review")
@Table(name = "TBL_REVIEW_REPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewReply extends Reply {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REVIEW_ID")
    private Review review;


    // 단위 테스트용 생성자
    public ReviewReply(String replyContent, Member member, Review review) {
        super(replyContent);
        this.member = member;
        this.review = review;
    }

    public void setReviewReplyContent(String replyContent) {
        super.setReplyContent(replyContent);
    }


    @Builder
    public ReviewReply(Long id, String replyContent, Review review, Member member) {
        super(id, replyContent);
        this.review = review;
        this.member = member;
    }
}
