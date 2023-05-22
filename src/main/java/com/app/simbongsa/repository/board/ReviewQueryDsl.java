package com.app.simbongsa.repository.board;

import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.entity.board.FreeBoard;
import com.app.simbongsa.search.admin.AdminBoardSearch;
import com.app.simbongsa.entity.board.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryDsl {
    /*시퀀스*/
    public Review getCurrentSequence_QueryDsl();

    /* 유저별 후기게시판 목록 조회 */
    public Page<Review> findByMemberId(Pageable pageable, MemberDTO memberDTO);

    /* 후기게시판 삭제*/
/*    public Review deleteById(Review review);*/

    /* 후기게시판 수정 */

    //    후기게시판 전체 조회(페이징)
    public Page<Review> findAllWithPaging(AdminBoardSearch adminBoardSearch, Pageable pageable);

    //    세션에 담긴 id 값 받아와서 내가 작성한 자유 게시글 리스트 가져오기
    public List<Review> findReviewListByMemberId(Pageable pageable, Long memberId);

    //    메인 인기순 목록 조회
    public List<Review> findAllWithPopularReview();

    //    후기 게시판 상세페이지 조회
    public Optional<Review> findByIdForDetail_QueryDsl(Long reviewId);

    /* 마이페이지 작성한 후기게시물 상세 조회*/
    public Optional<Review> findByIdForMyDetail(Long reviewId);

    //    인기순 목록 조회 - 무한스크롤
    public Slice<Review> findAllByLikeCountReviewPaging_QueryDSL(Pageable pageable);

    //    최신순 목록 조회 - 무한스크롤
    public Slice<Review> findAllByIdReviewPaging_QueryDSL(Pageable pageable);
}
