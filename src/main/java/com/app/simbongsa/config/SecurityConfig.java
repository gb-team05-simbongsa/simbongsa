package com.app.simbongsa.config;

import com.app.simbongsa.service.member.MemberOAuthService;
import com.app.simbongsa.type.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {
    private static final String ADMIN_PATH = "/admin/**";
    private static final String FUNDING_PATH = "/funding/**";
    private static final String MYPAGE_PATH = "/mypage/**";

    private static final String INQUIRY_PAGE = "/inquiry/inquiry-write";
    private static final String COMMUNITY_FREE_PAGE = "/community/free-create";
    private static final String COMMUNITY_REVIEW_PAGE = "/community/review-create";
    private static final String SUPPORT_PAGE = "/support/support-write";
    private static final String LOGIN_PAGE = "/member/login";
    private static final String LOGIN_PROCESSING_URL = "/member/login";
    private static final String LOGOUT_URL = "/member/logout";
    private static final String LOGOUT_SUCCESS_URL = "/main/";
    private static final String REMEMBER_ME_TOKEN_KEY = "have a nice day";
    private static final int REMEMBER_ME_TOKEN_EXPIRED = 86400 * 14;

    private static final String IGNORE_MEMBER_PATH = "/member/**";
    private static final String IGNORE_FAVICON = "/favicon.ico";
    private static final String IGNORE_MAIN_PATH = "/main/**";
    private static final String IGNORE_VOLUNTEER_WORK_PATH = "/volunteer-work/**";
    private static final String IGNORE_CALENDAR_PATH = "/calendar/**";
    private static final String IGNORE_WELFARECENTER_PATH = "/wellFareCenter/**";

    private final AccessDeniedHandler accessDeniedHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final UserDetailsService userDetailsService;
    private final MemberOAuthService memberOAuthService;

//    비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
//        WebSecurity에서 관여하지 않을 경로
        return web -> web.ignoring()
                .mvcMatchers(IGNORE_FAVICON) //favicon은 필터에서 제외
                /*.antMatchers(IGNORE_MAIN_PATH)
                .antMatchers(IGNORE_VOLUNTEER_WORK_PATH)
                .antMatchers(IGNORE_CALENDAR_PATH)
                .antMatchers(IGNORE_WELFARECENTER_PATH)*/
                /*일단 admin 편하게 들어갈 수 있게 한건데 나중에 수정하기*/
                /*.antMatchers(IGNORE_MEMBER_PATH)*/
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()); //static 경로도 필터에서 제외
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(ADMIN_PATH).hasRole(Role.ADMIN.name())
                /* 작업하기 편하도록 임시방편 */
                .antMatchers(MYPAGE_PATH).hasRole(Role.ADMIN.name())
                .antMatchers(SUPPORT_PAGE).hasRole(Role.ADMIN.name())
                .antMatchers(COMMUNITY_FREE_PAGE).hasRole(Role.ADMIN.name())
                .antMatchers(COMMUNITY_REVIEW_PAGE).hasRole(Role.ADMIN.name())
                .antMatchers(INQUIRY_PAGE).hasRole(Role.ADMIN.name())

                .antMatchers(MYPAGE_PATH).hasRole(Role.MEMBER.name())
                /*일단 funding 편하게 들어갈 수 있게 한건데 나중에 수정하기*/
                /*.antMatchers(FUNDING_PATH).hasRole(Role.MEMBER.name())*/
                .antMatchers(SUPPORT_PAGE).hasRole(Role.MEMBER.name())
                .antMatchers(COMMUNITY_FREE_PAGE).hasRole(Role.MEMBER.name())
                .antMatchers(COMMUNITY_REVIEW_PAGE).hasRole(Role.MEMBER.name())
                .antMatchers(INQUIRY_PAGE).hasRole(Role.MEMBER.name())
                .and()
                .csrf().disable()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler) //인가 실패
                .authenticationEntryPoint(authenticationEntryPoint); //인증 실패

        log.info(userDetailsService.toString());

        http
                .formLogin()
                .loginPage(LOGIN_PAGE)
                .usernameParameter("memberEmail")
                .passwordParameter("memberPassword")
                .loginProcessingUrl(LOGIN_PROCESSING_URL)
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_URL))
                .logoutSuccessUrl(LOGOUT_SUCCESS_URL)
                .invalidateHttpSession(Boolean.TRUE)
                .and()
                .rememberMe()
                .rememberMeParameter("remember-me")
                .key(REMEMBER_ME_TOKEN_KEY)
                .tokenValiditySeconds(REMEMBER_ME_TOKEN_EXPIRED)
                .userDetailsService(userDetailsService)
                .authenticationSuccessHandler(authenticationSuccessHandler)
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(memberOAuthService);

        return http.build();
    }

}
