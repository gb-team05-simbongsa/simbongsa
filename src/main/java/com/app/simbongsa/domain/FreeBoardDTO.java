package com.app.simbongsa.domain;

import com.app.simbongsa.entity.board.FreeBoardReply;
import com.app.simbongsa.entity.file.FreeBoardFile;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class FreeBoardDTO {
    private Long id;
    private String boardTitle;
    private String boardContent;
    // 댓글 총
    private int replyCount;
    private MemberDTO memberDTO;
    private List<FreeBoardReply> freeBoardReplies;
    private List<FreeBoardFile> freeBoardFiles;

    @Builder
    public FreeBoardDTO(Long id, String boardTitle, String boardContent, MemberDTO memberDTO, List<FreeBoardReply> freeBoardReplies, List<FreeBoardFile> freeBoardFiles) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.memberDTO = memberDTO;
        this.freeBoardReplies = freeBoardReplies;
        this.freeBoardFiles = freeBoardFiles;
        this.replyCount = freeBoardReplies.size();

    }


}
