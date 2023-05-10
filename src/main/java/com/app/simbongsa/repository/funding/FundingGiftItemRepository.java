package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.entity.funding.FundingGift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingGiftItemRepository extends JpaRepository<FundingGift, Long>, FundingGiftItemQueryDsl{
}
