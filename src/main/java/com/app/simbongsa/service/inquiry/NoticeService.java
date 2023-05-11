package com.app.simbongsa.service.inquiry;

import com.app.simbongsa.domain.NoticeDTO;
import com.app.simbongsa.domain.PageDTO;
import com.app.simbongsa.entity.inquiry.Notice;
import com.app.simbongsa.search.admin.AdminNoticeSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeService {
//    목록 전체 조회(페이징)
    public Page<NoticeDTO> getNotice(Integer page);

//    페이지 정보 따로 들고가기
//    public PageDTO getPageInfo(AdminNoticeSearch adminNoticeSearch, Pageable pageable);

//    공지사항 상세보기
//    public NoticeDTO getNoticeDetail(Long id);

//    default NoticeDTO toNoticeDTO(Notice notice, Long noticeCount) {
//        return NoticeDTO.builder()
//                .id(notice.getId())
//                .noticeTitle(notice.getNoticeTitle())
//                .noticeContent(notice.getNoticeContent())
//                .createdDate(notice.getCreatedDate())
//                .updatedDate(notice.getUpdatedDate())
//                .noticeCount(noticeCount)
//                .build();
//    }

    default NoticeDTO toNoticeDTO(Notice notice) {
        return NoticeDTO.builder()
                .id(notice.getId())
                .noticeTitle(notice.getNoticeTitle())
                .noticeContent(notice.getNoticeContent())
                .createdDate(notice.getCreatedDate())
                .updatedDate(notice.getUpdatedDate())
                .build();
    }

//    default PageDTO toPageDTO(Page<Notice> notices) {
//        return PageDTO.builder()
//                .totalPage(notices.getTotalPages())
//                .totalElements(notices.getTotalElements())
//                .currentNumber(notices.getNumber())
//                .hasNext(notices.hasNext())
//                .hasPrevious(notices.hasPrevious())
//                .startPage((int)Math.ceil((notices.getNumber() + 1) / 5.0) * 5 - 5 + 1)
//                .endPage((int)Math.ceil((notices.getNumber() + 1) / 5.0) * 5)
//                .realEndPage((int) (Math.ceil(notices.getTotalPages() / 5)))
//                .build();
//    }
}
