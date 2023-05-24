package com.app.simbongsa.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/service/*")
@RequiredArgsConstructor
@Slf4j
public class ServiceInfoController {
    @GetMapping("privacy")
    public String servicePrivacy() { return "/service-info/service-conditions.html";}


}