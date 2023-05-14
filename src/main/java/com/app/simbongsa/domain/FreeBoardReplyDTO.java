package com.app.simbongsa.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
@NoArgsConstructor
public class FreeBoardReplyDTO {
    private Long id;
    private String freeBoardReplyContent;
    private LocalDateTime registerDate;

    private MemberDTO memberDTO;
    private FreeBoardDTO freeBoardDTO;


    @Builder
    public FreeBoardReplyDTO(Long id, String freeBoardReplyContent, LocalDateTime registerDate, MemberDTO memberDTO, FreeBoardDTO freeBoardDTO) {
        this.id = id;
        this.freeBoardReplyContent = freeBoardReplyContent;
        this.registerDate = registerDate;
        this.memberDTO = memberDTO;
        this.freeBoardDTO = freeBoardDTO;
    }

    @Builder
    public FreeBoardReplyDTO(Long id, String freeBoardReplyContent, LocalDateTime registerDate, MemberDTO memberDTO) {
        this.id = id;
        this.freeBoardReplyContent = freeBoardReplyContent;
        this.registerDate = registerDate;
        this.memberDTO = memberDTO;
    }
}
