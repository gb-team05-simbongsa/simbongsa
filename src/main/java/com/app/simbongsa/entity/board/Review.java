package com.app.simbongsa.entity.board;

import com.app.simbongsa.entity.file.FreeBoardFile;
import com.app.simbongsa.entity.file.ReviewFile;
import com.app.simbongsa.entity.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @ToString(callSuper = true, exclude = "member")
@Table(name = "TBL_REVIEW")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends Board {

    @Column(columnDefinition = "integer default 0")
    private Integer reviewReplyCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "review", cascade = CascadeType.REMOVE)
    private List<ReviewReply> reviewReplies = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "review", cascade = CascadeType.REMOVE)
    private List<ReviewFile> reviewFiles = new ArrayList<>();

    @Builder
    public Review(Long id, String boardTitle, String boardContent, Member member, List<ReviewFile> reviewFiles) {
        super(id, boardTitle, boardContent);
        this.member = member;
        this.reviewFiles = reviewFiles;
    }

    public void setReviewFiles(List<ReviewFile> reviewFiles) {
        this.reviewFiles = reviewFiles;
    }

    public void setReviewReplyCount(Integer reviewReplyCount) {
        this.reviewReplyCount = reviewReplyCount;
    }
}
