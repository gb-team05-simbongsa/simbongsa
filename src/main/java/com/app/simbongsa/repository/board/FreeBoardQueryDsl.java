package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.board.FreeBoard;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface FreeBoardQueryDsl {
    //    인기순 목록 조회
    public List<FreeBoard> findAllWithPopularFreeBoard();

    //    자유게시판 전체 조회(페이징)
    public Page<FreeBoard> findAllWithPaging(Pageable pageable);

    /* 유저가 작성한 자유게시물 조회(페이징처리) */
    public Page<FreeBoard> findByUserId(Pageable pageable, Long userId);

    //    세션에 담긴 id 값 받아와서 내가 작성한 자유 게시글 리스트 가져오기
    public List<FreeBoard> findFreeBoardListByUserId(Pageable pageable, Long userId);

    //    자유게시판 상세페이지 조회
    public Optional<FreeBoard> findByIdForDetail(Long freeBoardId);
}
