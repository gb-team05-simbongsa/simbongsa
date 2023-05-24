package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.board.ReviewReply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ReviewReplyQueryDsl {

    // 전체 조회 ( 페이징 )
    public Slice<ReviewReply> findAllByReviewReplyWithPaging(Long boardId, Pageable pageable);

    // 댓글 갯수
    public Long getReplyCount_QueryDsl(Long reviewId);

    // 댓글 삭제
    public void deleteByReviewId(Long reviewId);
}
