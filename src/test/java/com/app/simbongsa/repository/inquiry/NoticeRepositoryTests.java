package com.app.simbongsa.repository.inquiry;

import com.app.simbongsa.search.admin.AdminNoticeSearch;
import com.app.simbongsa.entity.inquiry.Notice;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Arrays;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class NoticeRepositoryTests {
    @Autowired
    private NoticeRepository noticeRepository;

//    공지사항 더미 넣기
    @Test
    public void saveTest() {
        for(int i = 1; i <= 100; i++) {
            Notice notice = new Notice("제목" + i, "내용" + i);
            noticeRepository.save(notice);
        }
    }

//    공지사항 전체 조회(페이징)
    @Test
    public void findAllWithPagingTest() {
        AdminNoticeSearch adminNoticeSearch = new AdminNoticeSearch();
//        adminNoticeSearch.setNoticeTitle("3");
        adminNoticeSearch.setNoticeTitle("5");
        Page<Notice> foundNotice = noticeRepository.findAllWithPaging(adminNoticeSearch, PageRequest.of(0, 5));
        foundNotice.stream().map(Notice::toString).forEach(log::info);
        log.info("=============================" + foundNotice.getTotalElements());
    }

//    공지사항 상세보기
    @Test
    public void findByIdTest() {
        noticeRepository.findById(291L).ifPresent(notice -> log.info(notice.toString()));
    }

//    공지사항 삭제
    @Test
    public void deleteTest() {
        Long[] ids = {291L, 292L};
        noticeRepository.deleteAllById(Arrays.asList(ids));
    }
}
