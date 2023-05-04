package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.board.ReviewReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingFileRepository extends JpaRepository<ReviewReply, Long>, FundingFileQueryDsl {
}
