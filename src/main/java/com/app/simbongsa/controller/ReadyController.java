package com.app.simbongsa.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class ReadyController {
    @GetMapping("ready")
    public String readyService() {
        return "error/unReady.html";
    }

    @GetMapping("ready-admin")
    public String readyServiceAdmin() {
        return "error/unReadyAdmin.html";
    }

}
