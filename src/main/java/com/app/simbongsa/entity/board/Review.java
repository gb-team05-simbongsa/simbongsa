package com.app.simbongsa.entity.board;

import com.app.simbongsa.entity.file.ReviewFile;
import com.app.simbongsa.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @ToString(callSuper = true, exclude = "user")
@Table(name = "TBL_REVIEW")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends Board {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "review")
    List<ReviewReply> riviewReplies;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "review")
    private List<ReviewFile> reviewFiles;

    /* 단위테스트 위한 생성자 생성 */
    public Review(String boardTitle, String boardContent, User user) {
        super(boardTitle, boardContent);
        this.user = user;
    }
}
