package com.app.simbongsa.service.funding;

import com.app.simbongsa.repository.funding.FundingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("funding") @Primary
public class FundingServiceImpl implements FundingService {
    private final FundingRepository fundingRepository;
}
