package com.app.simbongsa.entity.board;

import com.app.simbongsa.entity.user.User;
import com.querydsl.core.types.dsl.StringPath;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @ToString(callSuper = true)
@Table(name = "TBL_FREE_BOARD")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeBoard extends Board {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "freeBoard")
    List<FreeBoardReply> freeBoardReplies;

    public FreeBoard(String boardTitle, String boardContent, User user) {
        super(boardTitle, boardContent);
        this.user = user;
    }
}
