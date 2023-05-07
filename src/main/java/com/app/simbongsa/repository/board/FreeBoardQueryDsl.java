package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.board.FreeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FreeBoardQueryDsl {
    public List<FreeBoard> findAllWithPopularFreeBoard();

//    자유게시판 전체 조회(페이징)
    public Page<FreeBoard> findAllWithPaging(Pageable pageable);

    /* 유저가 작성한 자유게시물 조회(페이징처리) */
    public Page<FreeBoard> findByUserId(Pageable pageable, Long userId);
}
