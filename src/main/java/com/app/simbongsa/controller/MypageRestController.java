package com.app.simbongsa.controller;

import com.app.simbongsa.domain.InquiryDTO;
import com.app.simbongsa.domain.NoticeDTO;
import com.app.simbongsa.entity.inquiry.Inquiry;
import com.app.simbongsa.service.inquiry.InquiryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mypages/*")
@RequiredArgsConstructor
@Slf4j
public class MypageRestController {
    private final InquiryService inquiryService;

    @PostMapping("inquiry-details")
    public InquiryDTO inquiryDetail(Long id) {
        return inquiryService.getInquiryDetail(id);
    }

    @PostMapping("inquiry-delete")
    public void deleteByInquiry(Long id) {
        inquiryService.deleteByInquiryId(id);
    }
}
