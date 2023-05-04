package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.board.Board;
import com.app.simbongsa.entity.funding.FundingItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingItemRepository extends JpaRepository<FundingItem, Long>, FundingItemQueryDsl {
}
