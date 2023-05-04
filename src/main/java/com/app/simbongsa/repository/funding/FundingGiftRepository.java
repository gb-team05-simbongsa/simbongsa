package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.board.Board;
import com.app.simbongsa.entity.funding.FundingGift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingGiftRepository extends JpaRepository<FundingGift, Long>, FundingGiftQueryDsl {
}
