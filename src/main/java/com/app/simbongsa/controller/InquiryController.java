package com.app.simbongsa.controller;

import com.app.simbongsa.domain.NoticeDTO;
import com.app.simbongsa.search.admin.AdminNoticeSearch;
import com.app.simbongsa.service.inquiry.InquiryService;
import com.app.simbongsa.service.inquiry.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/inquiry/*")
@RequiredArgsConstructor
@Slf4j
public class InquiryController {
    private final NoticeService noticeService;
    private final InquiryService inquiryService;

    @GetMapping("notice")
    public String notice(Model model) {
        AdminNoticeSearch adminNoticeSearch = new AdminNoticeSearch();
        List<NoticeDTO> notice = noticeService.getNotice(adminNoticeSearch, PageRequest.of(0, 5));

        model.addAttribute("notice", notice);
        return "customerCenter/notice";
    }

    @GetMapping("notice-detail")
    public String noticeDetail() {
        return "customerCenter/notice-detail";
    }

    @GetMapping("faq")
    public String faq() {
        return "customerCenter/faq";
    }

    @GetMapping("faq-detail")
    public String faqDetail() {
        return "customerCenter/faq-detail";
    }

    @GetMapping("inquiry-write")
    public String inquiryWrite() {
        return "customerCenter/inquiry-write";
    }

    @GetMapping("search")
    public String search() {
        return "customerCenter/search";
    }

    @GetMapping("header")
    public String header() {
        return "customerCenter/header";
    }

}
