package com.app.simbongsa.controller;

import com.app.simbongsa.domain.NoticeDTO;
import com.app.simbongsa.domain.PageDTO;
import com.app.simbongsa.search.admin.AdminNoticeSearch;
import com.app.simbongsa.service.inquiry.InquiryService;
import com.app.simbongsa.service.inquiry.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins/*")
@RequiredArgsConstructor
public class AdminRestController {
    private final NoticeService noticeService;
    private final InquiryService inquiryService;

//    @GetMapping("notice-lists")
//    public void noticeList(Integer page, Model model) {
//        AdminNoticeSearch adminNoticeSearch = new AdminNoticeSearch();
//        Page<NoticeDTO> notices = noticeService.getNotice(page, adminNoticeSearch);
//
//        model.addAttribute("noticeDTOS", notices.getContent());
//        model.addAttribute("pageDTO", new PageDTO(notices));
//    }


    @PostMapping("notice-details")
    public NoticeDTO noticeDetail(Long id) {
        return noticeService.getNoticeDetail(id);
    }

    @PostMapping("notices-delete")
    public void noticesDelete() {

    }
}
