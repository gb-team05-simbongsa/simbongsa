package com.app.simbongsa.controller;

import com.app.simbongsa.exception.CustomAuthenticationException;
import com.app.simbongsa.exception.LoginFailedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

public class GlobalExceptionHandler {
    @ExceptionHandler(CustomAuthenticationException.class)
    protected RedirectView handleCustomAuthenticationException(CustomAuthenticationException e, HttpSession session){
        session.removeAttribute("id");
        session.removeAttribute("memberEmail");
        session.removeAttribute("role");
        session.invalidate();
        return new RedirectView("/member/login");
    }

    @ExceptionHandler(LoginFailedException.class)
    protected RedirectView handleLoginFailedException(LoginFailedException e){
        return new RedirectView("/member/login?check=false");
    }
}
