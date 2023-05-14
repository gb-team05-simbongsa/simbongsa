package com.app.simbongsa.domain;

import com.app.simbongsa.entity.board.ReviewReply;
import com.app.simbongsa.entity.file.ReviewFile;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReviewDTO {
    private Long id;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    // 댓글 총
    private int replyCount;
    private MemberDTO memberDTO;
    private List<ReviewReply> reviewReplies;
    private List<ReviewFile> reviewFiles;


    @Builder
    public ReviewDTO(Long id, String boardTitle, String boardContent, LocalDateTime createdDate, LocalDateTime updatedDate, int replyCount, MemberDTO memberDTO, List<ReviewReply> reviewReplies, List<ReviewFile> reviewFiles) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.replyCount = replyCount;
        this.memberDTO = memberDTO;
        this.reviewReplies = reviewReplies;
        this.reviewFiles = reviewFiles;
    }
}
