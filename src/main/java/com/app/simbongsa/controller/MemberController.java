package com.app.simbongsa.controller;

import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    /* 회원 가입 페이지 이동*/
    @GetMapping("join")
    public String gotoJoinForm(MemberDTO memberDTO) {
        return "join-login/email-join";
    }

    /* 회원가입후 login 페이지로 이동*/
    @PostMapping("join")
    public RedirectView join(MemberDTO memberDTO) {
        memberService.join(memberDTO, passwordEncoder);
        return new RedirectView("/member/login");
    }

    /* 카카오, 네이버, 이메일 회원가입 선택 페이지 */
    @GetMapping("join-select")
    public String joinSelect() {
        return "join-login/join-select";
    }

    /* 로그인 페이지로 이동 */
    @GetMapping("login")
    public String goToLoginForm() {return "join-login/email-login";}

    /* 로그인 선택 페이지 */
    @GetMapping("login-select")
    public String loginSelect() {
        return "join-login/login-select";
    }

    /* 비밀번호 찾기 페이지 */
    @GetMapping("find-password")
    public String findPassword() {
        return "join-login/find-password";
    }

    /* 비밀번호 재설정 페이지 */
    @GetMapping("change-password")
    public String changePassword() {
        return "join-login/change-password";
    }

    /* 로그아웃 */
    @GetMapping("logout")
    public void goToLogout() {;}
}
