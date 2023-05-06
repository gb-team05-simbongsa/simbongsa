package com.app.simbongsa.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community/*")
@RequiredArgsConstructor
@Slf4j
public class CommunityController {
    @GetMapping("free-board")
    public String freeBoard() {return "community/free-board";}

    @GetMapping("free-create")
    public String freeCreate() {return "community/free-create";}

    @GetMapping("free-detail")
    public String freeDetail() {return "community/free-detail";}

    @GetMapping("review-board")
    public String reviewBoard() {return "community/review-board";}

    @GetMapping("review-create")
    public String reviewCreate() {return "community/review-create";}

    @GetMapping("review-detail")
    public String reviewDerail() {return "community/review-detail";}
}
