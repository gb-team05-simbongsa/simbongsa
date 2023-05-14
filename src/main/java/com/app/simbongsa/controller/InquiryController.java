package com.app.simbongsa.controller;

import com.app.simbongsa.domain.InquiryDTO;
import com.app.simbongsa.domain.NoticeDTO;
import com.app.simbongsa.domain.PageDTO;
import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.search.admin.AdminNoticeSearch;
import com.app.simbongsa.service.inquiry.InquiryService;
import com.app.simbongsa.service.inquiry.NoticeService;
import com.app.simbongsa.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/inquiry/*")
@RequiredArgsConstructor
@Slf4j
public class InquiryController {
    private final NoticeService noticeService;
    private final InquiryService inquiryService;
    private final MemberService memberService;

    @GetMapping("notice")
    public String notice(Integer page, Model model) {
        page = page == null ? 0 : page - 1;
        AdminNoticeSearch adminNoticeSearch = new AdminNoticeSearch();
        Page<NoticeDTO> noticeDTOS = noticeService.getNotice(page, adminNoticeSearch);

        model.addAttribute("noticeDTOS", noticeDTOS.getContent());
        model.addAttribute("pageDTO", new PageDTO(noticeDTOS));
        return "customerCenter/notice";
    }

    @GetMapping("notice-detail/{id}")
    public String noticeDetail(@PathVariable Long id, Model model) {
        NoticeDTO noticeDetail = noticeService.getNoticeDetail(id);

        model.addAttribute("notice", noticeDetail);
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
    public String gotoInquiryWrite(InquiryDTO inquiryDTO) {
        return "customerCenter/inquiry-write";
    }

    @PostMapping("inquiry-write")
    public String inquiryWrite(InquiryDTO inquiryDTO, @AuthenticationPrincipal UserDetail userDetail){
        inquiryDTO.setMemberDTO(memberService.getMemberById(userDetail.getId()));
        inquiryService.saveInquiry(inquiryDTO);
        return "mypage/my-question";
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
