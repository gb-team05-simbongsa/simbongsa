package com.app.simbongsa.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/volunteer-work/*")
@RequiredArgsConstructor
@Slf4j
public class VolunteerWorkController {
    @GetMapping("work-list")
    public String workList() { return "volunteer-work/work.html";}

    @GetMapping("work-detail")
    public String workDetail() { return "volunteer-work/work-detail.html";}

    @GetMapping("work-search")
    public String workSearch() { return "volunteer-work/work-search2.html";}

    @GetMapping("work-modal")
    public String workModal() { return "volunteer-work/work-apply-modal.html";}

}
