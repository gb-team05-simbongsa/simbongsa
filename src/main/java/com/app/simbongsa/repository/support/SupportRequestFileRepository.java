package com.app.simbongsa.repository.support;

import com.app.simbongsa.entity.board.ReviewReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportRequestFileRepository extends JpaRepository<ReviewReply, Long>, SupportRequestFileQueryDsl {
}
