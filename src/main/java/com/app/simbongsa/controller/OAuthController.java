package com.app.simbongsa.controller;

import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class OAuthController {

    private final MemberService memberService;

    @GetMapping("/")
    public RedirectView oAuthLogin(HttpSession session, RedirectAttributes redirectAttributes){
        log.info(" ------------------- 로그인 처리 후 맨 마지막 컨트롤러 ------------------------------------- ");
        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
        log.info(memberDTO + "세션 가져옴");
        if (memberDTO.getId() == null) {
            redirectAttributes.addFlashAttribute("member", memberDTO);
            log.info(memberDTO.toString());
            return new RedirectView("/member/join");
        }
        log.info("==================================================");
        log.info(memberDTO.toString());
        return new RedirectView("/main/");

    }
}