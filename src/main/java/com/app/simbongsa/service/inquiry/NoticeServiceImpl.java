package com.app.simbongsa.service.inquiry;

import com.app.simbongsa.repository.inquiry.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("notice") @Primary
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;
}
