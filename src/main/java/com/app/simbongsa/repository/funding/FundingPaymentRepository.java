package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.board.Board;
import com.app.simbongsa.entity.funding.FundingPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingPaymentRepository extends JpaRepository<FundingPayment, Long>, FundingPaymentQueryDsl {
}
