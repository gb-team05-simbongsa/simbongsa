package com.app.simbongsa.service.member;

import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.domain.OAuthAttributes;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberOAuthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MemberRepository memberRepository;
    private final HttpSession session;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=" + userRequest);
//        사용자의 로그인 완료 후의 정보를 담기위한 준비
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
//        로그인된 사용자의 정보 불러오기
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        log.info("/////////////////////////////////////" + oAuth2User);

//        어떤 기업의 OAuth를 사용했는 지의 구분(naver, kakao 등)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        log.info("\\//\\//\\//\\//\\//\\//\\//\\//" + registrationId);
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();
        log.info("*******************************" + userNameAttributeName);
        // naver, kakao 로그인 구분
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        log.info("--------------------------------------- 로그인 거치기");
        log.info(attributes.getName());
        log.info(attributes.getEmail());
        log.info(attributes.getMobile());
        Member member = saveOrUpdate(attributes);

//        OAuth를 통해 전달받은 정보를 DTO로 변환하여 session에 저장
//        session에 객체를 저장하기 위해 직렬화 사용(다시 가져올 때에는 역직렬화를 통해 원본 객체 생성)
//        회원 번호를 사용하는 것 보다 OAuth 인증에 작성된 이메일을 사용하는 것이 올바르다.
        session.setAttribute("member", new MemberDTO(member));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(member.getMemberRole().getSecurityRole())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private Member saveOrUpdate(OAuthAttributes attributes) {
        Member foundMember = memberRepository.findByMemberEmail(attributes.getEmail())
                .map(member -> member.update(attributes.getName(), attributes.getEmail()))
                .orElse(attributes.toEntity());

        return memberRepository.save(foundMember);
    }
}
