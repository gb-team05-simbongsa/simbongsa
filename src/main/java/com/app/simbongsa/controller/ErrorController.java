package com.app.simbongsa.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error/*")
@RequiredArgsConstructor
@Slf4j
public class ErrorController {

    @GetMapping("400")
    public String error400() {
        return "error/400";
    }
    @GetMapping("401")
    public String error401() {
        return "error/401";
    }
    @GetMapping("403")
    public String error403() {
        return "error/403";
    }
    @GetMapping("404")
    public String error404() {
        return "error/404";
    }
    @GetMapping("405")
    public String error405() {
        return "error/405";
    }
    @GetMapping("500")
    public String error500() {
        return "error/500";
    }
}
