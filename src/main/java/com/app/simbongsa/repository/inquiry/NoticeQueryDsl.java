package com.app.simbongsa.repository.inquiry;

import com.app.simbongsa.search.admin.AdminNoticeSearch;
import com.app.simbongsa.entity.inquiry.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeQueryDsl {

//    공지사항 전체 조회(페이징)
    public Page<Notice> findAllWithPaging(AdminNoticeSearch adminNoticeSearch, Pageable pageable);
}
