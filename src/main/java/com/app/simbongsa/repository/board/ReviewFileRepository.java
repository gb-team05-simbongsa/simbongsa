package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.board.ReviewReply;
import com.app.simbongsa.entity.file.ReviewFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewFileRepository extends JpaRepository<ReviewFile, Long>, ReviewFileQueryDsl {
}
