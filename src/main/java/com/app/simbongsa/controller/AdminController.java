package com.app.simbongsa.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/*")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
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
    public String notice() {
        return "admin/notice";
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
