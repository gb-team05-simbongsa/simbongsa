package com.app.simbongsa.service.inquiry;

import com.app.simbongsa.repository.inquiry.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("inquiry") @Primary
public class InquiryServiceImpl implements InquiryService {
    private final InquiryRepository inquiryRepository;
}
