package com.app.simbongsa.domain;

import com.app.simbongsa.entity.board.FreeBoardReply;
import com.app.simbongsa.entity.file.FreeBoardFile;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class FreeBoardDTO {
    private Long id;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    // 댓글 총
    private int replyCount;
    private MemberDTO memberDTO;
    private List<FreeBoardReply> freeBoardReplies;
    private List<FreeBoardFile> freeBoardFiles;

    @Builder
    public FreeBoardDTO(Long id, String boardTitle, String boardContent, LocalDateTime createdDate, LocalDateTime updatedDate, int replyCount, MemberDTO memberDTO, List<FreeBoardReply> freeBoardReplies, List<FreeBoardFile> freeBoardFiles) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.replyCount = replyCount;
        this.memberDTO = memberDTO;
        this.freeBoardReplies = freeBoardReplies;
        this.freeBoardFiles = freeBoardFiles;
    }
}
