package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.board.FreeBoardReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardReplyRepository extends JpaRepository<FreeBoardReply, Long>, FreeBoardReplyQueryDsl {
}
