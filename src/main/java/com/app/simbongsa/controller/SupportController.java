package com.app.simbongsa.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/support/*")
@RequiredArgsConstructor
@Slf4j
public class SupportController {

    @GetMapping("support-detail")
    public String supportDetail(){
        return "support/support-detail";
    }
    @GetMapping("support-list")
    public String supportList(){
        return "support/support-list";
    }
    @GetMapping("support-success")
    public String supportSuccess(){
        return "support/support-success";
    }
    @GetMapping("support-write")
    public String supportWrite(){
        return "support/support-write";
    }
}
