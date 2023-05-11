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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("notice") @Primary
@Slf4j
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    @Override
    public Page<NoticeDTO> getNotice(Integer page) {
        AdminNoticeSearch adminNoticeSearch = new AdminNoticeSearch();
        Page<Notice> notices = noticeRepository.findAllWithPaging(adminNoticeSearch, PageRequest.of(page, 5));
        List<NoticeDTO> noticeDTOS = notices.getContent().stream().map(this::toNoticeDTO).collect(Collectors.toList());
        return new PageImpl<>(noticeDTOS, notices.getPageable(), notices.getTotalElements());
    }

//    @Override
//    public PageDTO getPageInfo(AdminNoticeSearch adminNoticeSearch, Pageable pageable) {
//        Page<Notice> notices = noticeRepository.findAllWithPaging(adminNoticeSearch, pageable);
//        PageDTO pageDTO = toPageDTO(notices);
//
//        if(pageable.getOffset() % 5 == notices.getTotalPages()) {
//            pageDTO.setEndPage(notices.getTotalPages());
//        }
//
//        return pageDTO;
//    }


//    @Override
//    public NoticeDTO getNoticeDetail(Long id) {
//        toNoticeDTO(noticeRepository.findById(id).get());
//    }
}
