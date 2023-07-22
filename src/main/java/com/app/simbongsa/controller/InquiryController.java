package com.app.simbongsa.controller;

import com.app.simbongsa.domain.InquiryDTO;
import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.domain.NoticeDTO;
import com.app.simbongsa.domain.PageDTO;
import com.app.simbongsa.search.admin.AdminNoticeSearch;
import com.app.simbongsa.service.inquiry.InquiryService;
import com.app.simbongsa.service.inquiry.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/inquiry/*")
@RequiredArgsConstructor
@Slf4j
public class InquiryController {
    private final NoticeService noticeService;
    private final InquiryService inquiryService;

    @GetMapping("notice")
    public String notice(Integer page, String searchContent, Model model, InquiryDTO inquiryDTO) {
        page = page == null ? 0 : page - 1;
        searchContent = searchContent == null ? "" : searchContent;

        AdminNoticeSearch adminNoticeSearch = new AdminNoticeSearch();
        adminNoticeSearch.setNoticeTitle(searchContent);

        Page<NoticeDTO> noticeDTOS = noticeService.getNotice(page, adminNoticeSearch);

        model.addAttribute("noticeDTOS", noticeDTOS.getContent());
        model.addAttribute("pageDTO", new PageDTO(noticeDTOS));
        model.addAttribute("searchContent", searchContent);
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

    @GetMapping("faq-detail2")
    public String faqDetail2() {
        return "customerCenter/faq-detail2";
    }

    @GetMapping("faq-detail3")
    public String faqDetail3() {
        return "customerCenter/faq-detail3";
    }

    @GetMapping("faq-detail4")
    public String faqDetail4() {
        return "customerCenter/faq-detail4";
    }

    @GetMapping("faq-detail5")
    public String faqDetail5() {
        return "customerCenter/faq-detail5";
    }

    @GetMapping("inquiry-write")
    public String gotoInquiryWrite() {
        return "customerCenter/inquiry-write";
    }

    @PostMapping("inquiry-write")
    public RedirectView inquiryWrite(String inquiryTitle, String inquiryContent, HttpSession session){
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");

        InquiryDTO inquiryDTO = new InquiryDTO();
        inquiryDTO.setInquiryTitle(inquiryTitle);
        inquiryDTO.setInquiryContent(inquiryContent);
        inquiryDTO.setMemberDTO(memberDTO);

        inquiryService.saveInquiry(inquiryDTO);
        return new RedirectView("/mypage/my-question");
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
