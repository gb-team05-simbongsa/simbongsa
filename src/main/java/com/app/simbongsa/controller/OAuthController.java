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
//        새로 가입한 회원인 경우 로그인 후 회원 가입 페이지로 이동하여 추가적인 정보를 입력받을 수 있도록 함 이때 memberDTO를 Flash 속성으로 전달하여 폼 페이지에서 기존 정보를 미리 채워넣기
        if (memberDTO.getId() == null) {
            redirectAttributes.addFlashAttribute("member", memberDTO);
            log.info(memberDTO.toString());
            return new RedirectView("/member/join");
        }

//        memberDTO.getId()가 null이 아니라면 즉 이미 가입된 회원인 경우 main 화면으로 보내기
        log.info("==================================================");
        log.info(memberDTO.toString());
        return new RedirectView("/main/");

    }
}