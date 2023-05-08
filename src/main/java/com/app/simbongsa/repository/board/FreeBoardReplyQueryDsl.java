package com.app.simbongsa.repository.board;

import com.app.simbongsa.domain.ReplyDTO;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface FreeBoardReplyQueryDsl {

    // 전체 조회 ( 페이징 )
    public Page<ReplyDTO> findAllByFreeBoardReplyWithPaging(Long Id, Pageable pageable);

    // 댓글 갯수
    public Long getFreeBoardReplyCount(Long id);
}
