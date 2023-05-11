package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.entity.funding.FundingGift;
import com.app.simbongsa.entity.funding.FundingGiftItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingGiftItemRepository extends JpaRepository<FundingGiftItem, Long>, FundingGiftItemQueryDsl{
}
