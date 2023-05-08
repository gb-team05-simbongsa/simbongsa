package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.board.ReviewReply;
import com.app.simbongsa.entity.file.FundingFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingFileRepository extends JpaRepository<FundingFile, Long>, FundingFileQueryDsl {
}
