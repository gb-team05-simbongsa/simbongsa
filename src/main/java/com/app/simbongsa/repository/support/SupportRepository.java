package com.app.simbongsa.repository.support;

import com.app.simbongsa.entity.board.Board;
import com.app.simbongsa.entity.support.Support;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupportRepository extends JpaRepository<Support, Long>, SupportQueryDsl {
}
