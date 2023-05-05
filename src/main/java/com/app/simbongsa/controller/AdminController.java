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
