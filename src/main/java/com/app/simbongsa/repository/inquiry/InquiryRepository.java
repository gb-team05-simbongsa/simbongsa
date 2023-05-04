package com.app.simbongsa.repository.inquiry;

import com.app.simbongsa.entity.board.Board;
import com.app.simbongsa.entity.inquiry.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long>, InquiryQueryDsl {
}
