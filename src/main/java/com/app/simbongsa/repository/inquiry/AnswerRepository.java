package com.app.simbongsa.repository.inquiry;

import com.app.simbongsa.entity.inquiry.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long>, AnswerQueryDsl {
}
