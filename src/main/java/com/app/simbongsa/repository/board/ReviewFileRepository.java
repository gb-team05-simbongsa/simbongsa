package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.board.ReviewReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewFileRepository extends JpaRepository<ReviewReply, Long>, ReviewFileQueryDsl {
}
