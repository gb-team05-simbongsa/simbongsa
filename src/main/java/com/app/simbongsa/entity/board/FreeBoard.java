package com.app.simbongsa.entity.board;

import com.app.simbongsa.entity.file.FreeBoardFile;
import com.app.simbongsa.entity.member.Member;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @ToString(callSuper = true, exclude = {"member", "freeBoardReplies", "freeBoardFiles"})
@Table(name = "TBL_FREE_BOARD")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@DynamicInsert
public class FreeBoard extends Board {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "freeBoard")
    private List<FreeBoardReply> freeBoardReplies;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "freeBoard")
    private List<FreeBoardFile> freeBoardFiles;

    public FreeBoard(String boardTitle, String boardContent, Member member) {
        super(boardTitle, boardContent);
        this.member = member;
    }
}
