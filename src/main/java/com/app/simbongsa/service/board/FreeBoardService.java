package com.app.simbongsa.service.board;

import com.app.simbongsa.entity.board.FreeBoard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FreeBoardService {
//    최신순 무한스크롤 전체 목록
    public Slice<FreeBoard> getAllNewFreeBoards(Pageable pageable);

//    인기순 무한스크롤 전체 목록
    public Slice<FreeBoard> getAllLikeFreeBoards(Pageable pageable);

}
