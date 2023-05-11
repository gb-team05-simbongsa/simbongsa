package com.app.simbongsa.controller;

import com.app.simbongsa.domain.NoticeDTO;
import com.app.simbongsa.domain.PageDTO;
import com.app.simbongsa.search.admin.AdminNoticeSearch;
import com.app.simbongsa.service.inquiry.InquiryService;
import com.app.simbongsa.service.inquiry.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin/*")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final NoticeService noticeService;
    private final InquiryService inquiryService;

//    회원관리 - user.html
//    봉사관리 - volunteer.html
//    문의관리 - inquiry.html
//    공지사항 - notice.html
//    펀딩관리 - funding.html
//    공양미  충전내역 - payment.html
//    공양미 환전요청 - gongyangmi-refund.html
//    후원요청 - sponsorship-request.html
//    활동후기관리 - activity-review-board.html
//    봉사모집자유게시판(자유게시판) - volunteer-recruitment-board.html

    @GetMapping("activity-review-board")
    public String activityReviewBoard() {
        return "admin/activity-review-board";
    }

    @GetMapping("funding")
    public String funding() {
        return "admin/funding";
    }

    @GetMapping("gongyangmi-refund")
    public String gongyangmiRefund() {
        return "admin/gongyangmi-refund";
    }

    @GetMapping("inquiry")
    public String inquiry() {
        return "admin/inquiry";
    }

    @GetMapping("notice")
    public String notice(Integer page, Model model) {
        page = page == null ? 0 : page - 1;
        AdminNoticeSearch adminNoticeSearch = new AdminNoticeSearch();
        Page<NoticeDTO> notices = noticeService.getNotice(page);

        model.addAttribute("noticeDTOS", notices.getContent());
        model.addAttribute("pageDTO", new PageDTO(notices));
        return "admin/notice";
    }

    @PostMapping("notice-detail")
    @ResponseBody
    public void noticeDetail() {
//        noticeService.getNoticeDetail(291L);
    }

    @GetMapping("payment")
    public String payment() {
        return "admin/payment";
    }

    @GetMapping("review")
    public String review() {
        return "admin/review";
    }

    @GetMapping("sponsorship-request")
    public String sponsorshipRequest() {
        return "admin/sponsorship-request";
    }

    @GetMapping("storage")
    public String storage() {
        return "admin/storage";
    }

    @GetMapping("user")
    public String user() {
        return "admin/user";
    }

    @GetMapping("volunteer")
    public String volunteer() {
        return "admin/volunteer";
    }

    @GetMapping("volunteer-recruitment-board")
    public String volunteerRecruitmentBoard() {
        return "admin/volunteer-recruitment-board";
    }
}
