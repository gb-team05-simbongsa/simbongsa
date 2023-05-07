package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.board.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewQueryDsl {
    /* 유저별 후기게시판 목록 조회 */
    public Page<Review> findByUserId(Pageable pageable, Long userId);

    /* 후기게시판 삭제*/
/*    public Review deleteById(Review review);*/

    /* 후기 게시판 수정 */

//    후기 목록 전체 조회(페이징)
    public Page<Review> findAllWithPaging(Pageable pageable);
}
