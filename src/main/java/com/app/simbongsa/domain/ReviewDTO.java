package com.app.simbongsa.domain;

import lombok.Data;

@Data
//@Component
public class ReviewDTO {
    private Long id;
    private String BoardTitle;
    private String BoardContent;

    private MemberDTO memberDTO;
    private FileDTO fileDTO;
    private FreeBoardReplyDTO freeBoardReplyDTO;

    public ReviewDTO(Long id, String boardTitle, String boardContent, MemberDTO memberDTO, FileDTO fileDTO, FreeBoardReplyDTO freeBoardReplyDTO) {
        this.id = id;
        this.BoardTitle = boardTitle;
        this.BoardContent = boardContent;
        this.memberDTO = memberDTO;
        this.fileDTO = fileDTO;
        this.freeBoardReplyDTO = freeBoardReplyDTO;
    }
}
