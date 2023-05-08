package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.board.FreeBoard;
import com.app.simbongsa.entity.board.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryDsl {
    /* 유저별 후기게시판 목록 조회 */
    public Page<Review> findByUserId(Pageable pageable, Long userId);

    /* 후기게시판 삭제*/
/*    public Review deleteById(Review review);*/

    /* 후기 게시판 수정 */

    //    후기 목록 전체 조회(페이징)
    public Page<Review> findAllWithPaging(Pageable pageable);

    //    세션에 담긴 id 값 받아와서 내가 작성한 자유 게시글 리스트 가져오기
    public List<Review> findReviewListByUserId(Pageable pageable, Long userId);

    //    인기순 목록 조회
    public List<Review> findAllWithPopularReview();

    //    후기 게시판 상세페이지 조회
    public Optional<Review> findByIdForDetail(Long reviewId);
}
