package com.app.simbongsa.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/*")
@RequiredArgsConstructor
@Slf4j
public class MypageController {

    @GetMapping("exchange-request")
    public String exchangeRequest(){
        return "mypage/exchange-request";
    }

    @GetMapping("my-funding-list")
    public String myFundingList(){
        return "mypage/my-funding-list";
    }

    @GetMapping("my-question")
    public String myQuestion(){
        return "mypage/my-question";
    }

    @GetMapping("my-review")
    public String myReview(){
        return "mypage/my-review";
    }

    @GetMapping("my-support-list")
    public String mySupportList(){
        return "mypage/my-support-list";
    }

    @GetMapping("rice-charge")
    public String riceCharge(){
        return "mypage/rice-charge";
    }

    @GetMapping("rice-list")
    public String riceList(){
        return "mypage/rice-list";
    }

    @GetMapping("support-request")
    public String supportRequest(){
        return "mypage/support-request";
    }

    @GetMapping("user-modify")
    public String userModify(){
        return "mypage/user-modify";
    }

    @GetMapping("volunteer-work-list")
    public String volunteerWorkList(){
        return "mypage/volunteer-work-list";
    }

    @GetMapping("withdraw-check")
    public String withdrawCheck(){
        return "mypage/withdraw-check";
    }

    @GetMapping("withdraw-login")
    public String withdrawLogin(){
        return "mypage/withdraw-login";
    }
}
