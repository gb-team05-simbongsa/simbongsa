package com.app.simbongsa.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/funding/*")
@RequiredArgsConstructor
@Slf4j
public class FundingController {
    @GetMapping("funding-creater-info")
    public String fundingCreate() {return "funding/funding-creater-info.html";}

    @GetMapping("funding-detail")
    public String fundingDetail() {return "funding/funding-detail.html";}

    @GetMapping("funding-gift")
    public String fundingGift() {return "funding/funding-gift.html";}

    @GetMapping("funding-initial-info")
    public String fundingInitial() {return "funding/funding-initial-info.html";}

    @GetMapping("funding-item")
    public String fundingItem() {return "funding/funding-item.html";}

    @GetMapping("funding-list")
    public String fundinglist() {return "funding/funding-list.html";}

    @GetMapping("funding-payment")
    public String fundingPayment() {return "funding/funding-payment.html";}

    @GetMapping("funding-plan")
    public String fundingPlan() {return "funding/funding-plan.html";}

    @GetMapping("funding-plan-main")
    public String fundingPlanMain() {return "funding/funding-plan-main.html";}

    @GetMapping("funding-project-plan")
    public String fundingProjectPlan() {return "funding/funding-project-plan.html";}

    @GetMapping("funding-result")
    public String fundingResult() {return "funding/funding-result.html";}

    @GetMapping("funding-start")
    public String fundingStart() {return "funding/funding-start.html";}

    @GetMapping("funding-topContent")
    public String fundingTopContent() {return "funding/funding-topContent.html";}
}
