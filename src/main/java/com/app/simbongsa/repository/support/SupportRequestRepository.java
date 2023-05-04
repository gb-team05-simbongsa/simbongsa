package com.app.simbongsa.repository.support;

import com.app.simbongsa.entity.board.Board;
import com.app.simbongsa.entity.support.SupportRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportRequestRepository extends JpaRepository<SupportRequest, Long>, SupportRequestQueryDsl {
}
