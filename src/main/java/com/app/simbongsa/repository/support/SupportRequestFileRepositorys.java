package com.app.simbongsa.repository.support;

import com.app.simbongsa.entity.board.ReviewReply;
import com.app.simbongsa.entity.file.SupportRequestFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SupportRequestFileRepositorys extends JpaRepository<SupportRequestFile, Long>, SupportQueryDsl {
}
