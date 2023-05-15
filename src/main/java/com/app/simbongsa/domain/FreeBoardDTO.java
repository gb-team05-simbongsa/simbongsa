package com.app.simbongsa.domain;

import com.app.simbongsa.entity.board.FreeBoardReply;
import com.app.simbongsa.entity.file.FreeBoardFile;
import lombok.Builder;
import lombok.Data;

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
    private Integer replyCount;
    private MemberDTO memberDTO;
    private List<FreeBoardReply> freeBoardReplies;
    private List<FreeBoardFile> freeBoardFiles;
    private List<FileDTO> fileDTOS;

    @Builder

    public FreeBoardDTO(Long id, String boardTitle, String boardContent, LocalDateTime createdDate, LocalDateTime updatedDate, Integer replyCount, MemberDTO memberDTO, List<FreeBoardReply> freeBoardReplies, List<FreeBoardFile> freeBoardFiles, List<FileDTO> fileDTOS) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.replyCount = replyCount;
        this.memberDTO = memberDTO;
        this.freeBoardReplies = freeBoardReplies;
        this.freeBoardFiles = freeBoardFiles;
        this.fileDTOS = fileDTOS;
    }
}
