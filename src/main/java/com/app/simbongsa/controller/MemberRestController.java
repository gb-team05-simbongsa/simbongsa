package com.app.simbongsa.controller;

import com.app.simbongsa.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/members/*")
@RequiredArgsConstructor
@Slf4j
public class MemberRestController {
    private final MemberService memberService;

    /*이메일 중복 검사*/
    @PostMapping("check-email")
    public Long overlapByMemberEmail(@RequestParam("memberEmail") String memberEmail) {
        log.info("들어옴ㅁㅇㄴㄻㅇㄻㄴㅇㄹㄴㅇㅁㄹㄴㅇㅁㄻㄴㅇㄻㄴㅇㄹ");
        return memberService.overlapByMemberEmail(memberEmail);
    }

}


