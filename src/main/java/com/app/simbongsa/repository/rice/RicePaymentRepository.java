package com.app.simbongsa.repository.rice;

import com.app.simbongsa.entity.board.Board;
import com.app.simbongsa.entity.rice.RicePayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RicePaymentRepository extends JpaRepository<RicePayment, Long>, RicePaymentQueryDsl {
}
