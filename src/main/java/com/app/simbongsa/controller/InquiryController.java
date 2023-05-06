package com.app.simbongsa.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inquiry/*")
@RequiredArgsConstructor
@Slf4j
public class InquiryController {

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

    @GetMapping("notice")
    public String notice() {
        return "customerCenter/notice";
    }

    @GetMapping("notice-detail")
    public String noticeDetail() {
        return "customerCenter/notice-detail";
    }

    @GetMapping("search")
    public String search() {
        return "customerCenter/search";
    }

}
