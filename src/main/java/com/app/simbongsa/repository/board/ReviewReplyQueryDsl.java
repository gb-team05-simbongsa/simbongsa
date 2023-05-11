package com.app.simbongsa.repository.board;

import com.app.simbongsa.domain.FreeBoardDTO;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface ReviewReplyQueryDsl {

    // 전체 조회 ( 페이징 )
    public Page<FreeBoardDTO> findAllByReviewReplyWithPaging(Long Id, Pageable pageable);

    // 댓글 갯수
    public Long findReplyCountByReviewId(Long id);
}
