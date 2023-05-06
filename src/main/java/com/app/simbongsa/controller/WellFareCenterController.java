package com.app.simbongsa.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wellFareCenter/*")
@RequiredArgsConstructor
@Slf4j
public class WellFareCenterController {
    @GetMapping("welfare-center-search")
    public String welfareCenterSearch() {
        return "welfare-center/welfare-center-search";
    }
}
