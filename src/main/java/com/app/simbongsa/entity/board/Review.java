package com.app.simbongsa.entity.board;

import com.app.simbongsa.entity.file.ReviewFile;
import com.app.simbongsa.entity.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @ToString(callSuper = true, exclude = {"member", "reviewReplies"})
@Table(name = "TBL_REVIEW")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends Board {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "review", cascade = CascadeType.REMOVE)
    private List<ReviewReply> reviewReplies;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "review", cascade = CascadeType.REMOVE)
    private List<ReviewFile> reviewFiles;

    /* 단위테스트 위한 생성자 생성 */
    public Review(String boardTitle, String boardContent, Member member) {
        super(boardTitle, boardContent);
        this.member = member;
    }
}
