package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.board.ReviewReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardFileRepository extends JpaRepository<ReviewReply, Long>, FreeBoardFileQueryDsl {
}
