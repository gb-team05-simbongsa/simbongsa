package com.app.simbongsa.repository.board;

import com.app.simbongsa.domain.FreeBoardDTO;
import com.app.simbongsa.entity.board.FreeBoardReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FreeBoardReplyQueryDsl {

    // 전체 조회 ( 페이징 )
    public Slice<FreeBoardReply> findAllByFreeBoardReplyWithPaging(Long freeBoardId, Pageable pageable);

    // 댓글 갯수
    public Long getReplyCount_QueryDsl(Long freeBoardId);

    // 댓글 삭제
    public void deleteByFreeBoardId(Long freeBoardId);
}
