package com.app.simbongsa.repository.support;

import com.app.simbongsa.entity.board.ReviewReply;
import com.app.simbongsa.entity.file.SupportRequestFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportRequestFileRepository extends JpaRepository<SupportRequestFile, Long>, SupportRequestFileQueryDsl {
}
