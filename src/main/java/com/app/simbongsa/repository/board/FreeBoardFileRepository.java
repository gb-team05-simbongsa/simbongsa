package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.board.ReviewReply;
import com.app.simbongsa.entity.file.FreeBoardFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardFileRepository extends JpaRepository<FreeBoardFile, Long>, FreeBoardFileQueryDsl {
}
