package com.app.simbongsa.service.inquiry;

import com.app.simbongsa.domain.NoticeDTO;
import com.app.simbongsa.domain.PageDTO;
import com.app.simbongsa.entity.inquiry.Notice;
import com.app.simbongsa.repository.inquiry.NoticeRepository;
import com.app.simbongsa.search.admin.AdminNoticeSearch;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Qualifier("notice") @Primary
@Slf4j
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    @Override
    public List<NoticeDTO> getNotice(AdminNoticeSearch adminNoticeSearch, Pageable pageable) {
        Page<Notice> notices = noticeRepository.findAllWithPaging(adminNoticeSearch, pageable);
        List<NoticeDTO> noticeDTOS = new ArrayList<>();
        notices.get().forEach(notice -> noticeDTOS.add(toNoticeDTO(notice)));
        return noticeDTOS;
    }

    @Override
    public PageDTO getPageInfo(AdminNoticeSearch adminNoticeSearch, Pageable pageable) {
        Page<Notice> notices = noticeRepository.findAllWithPaging(adminNoticeSearch, pageable);
        PageDTO pageDTO = toPageDTO(notices);

        if(notices.getTotalPages() < 5) {
            pageDTO.setEndPage(notices.getTotalPages());
        }

        return pageDTO;
    }


//    @Override
//    public NoticeDTO getNoticeDetail(Long id) {
//        toNoticeDTO(noticeRepository.findById(id).get());
//    }
}
