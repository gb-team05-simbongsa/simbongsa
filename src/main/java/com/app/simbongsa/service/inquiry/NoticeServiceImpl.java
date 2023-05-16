package com.app.simbongsa.service.inquiry;

import com.app.simbongsa.domain.NoticeDTO;
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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("notice") @Primary
@Slf4j
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;

    @Override
    public void saveNotice(NoticeDTO noticeDTO) {
        noticeRepository.save(toNoticeEntity(noticeDTO));
    }

    @Override
    public Page<NoticeDTO> getNotice(Integer page, AdminNoticeSearch adminNoticeSearch) {
        Page<Notice> notices = noticeRepository.findAllWithPaging_QueryDSL(adminNoticeSearch, PageRequest.of(page, 5));
        List<NoticeDTO> noticeDTOS = notices.getContent().stream().map(this::toNoticeDTO).collect(Collectors.toList());
        return new PageImpl<>(noticeDTOS, notices.getPageable(), notices.getTotalElements());
    }

    @Override
    public NoticeDTO getNoticeDetail(Long id) {
        return toNoticeDTO(noticeRepository.findById(id).get());
    }

    @Override
    public void setNotice(NoticeDTO noticeDTO) {
        Notice notice = toNoticeEntity(noticeDTO);
        updateNotice(notice, notice.getNoticeTitle(), notice.getNoticeContent());
        noticeRepository.save(notice);
    }

    @Override
    public void deleteNotice(List<Long> ids) {
        noticeRepository.deleteAllById(ids);
    }
}
