package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.board.FreeBoard;

import java.util.List;

public interface FreeBoardQueryDsl {
    public List<FreeBoard> findAllWithPopularFreeBoard();
}
