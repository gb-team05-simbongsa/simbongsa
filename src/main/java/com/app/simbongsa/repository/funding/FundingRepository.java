package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.board.Board;
import com.app.simbongsa.entity.funding.Funding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingRepository extends JpaRepository<Funding, Long>, FundingQueryDsl {
}
