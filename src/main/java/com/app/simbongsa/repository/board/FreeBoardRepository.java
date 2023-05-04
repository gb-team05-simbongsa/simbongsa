package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.board.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long>, FreeBoardQueryDsl {
}
