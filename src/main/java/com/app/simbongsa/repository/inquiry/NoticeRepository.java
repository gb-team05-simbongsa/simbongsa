package com.app.simbongsa.repository.inquiry;

import com.app.simbongsa.entity.board.Board;
import com.app.simbongsa.entity.inquiry.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeQueryDsl {
}
