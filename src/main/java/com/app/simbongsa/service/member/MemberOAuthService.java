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
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberOAuthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;
    private final HttpSession session;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info(" ------------------------------ OAuth 첫 엔트리 ----------------------------------- ");
//        사용자의 로그인 완료 후의 정보를 담기위한 준비
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
//        로그인된 사용자의 정보 불러오기
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

//        어떤 기업의 OAuth를 사용했는 지의 구분(naver, kakao 등)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();
        log.info(" ------------------------------- OAuth 구분 ------------------------------------- ");
        // naver, kakao 로그인 구분
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        log.info(attributes.getEmail());
        Member member = saveOrUpdate(attributes);
        log.info(member + " service OAuth 구분");

        log.info(" --------------------------- 데이터 넘기기 전 -------------------------------------------- ");
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(member.getMemberRole().getSecurityRole())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private Member saveOrUpdate(OAuthAttributes attributes) {
        log.info(" ------------------------------- 데이터 저장 분기처리 ----------------------------------- ");

//        데이터베이스에서 해당 이메일로 등록된 회원 찾기
        Optional<Member> foundMemberOptional = memberRepository.findByMemberEmail(attributes.getEmail());
        Member foundMember;

        if (foundMemberOptional.isPresent()) {
//            이미 가입된 회원이 있는 경우, 회원 정보 업데이트
            foundMember = foundMemberOptional.get();
//            가입된 회원이면 해당 회원의 정보를 가져와서 MemberDTO 객체를 생성하고 세션에 저장. 이때, MemberDTO는 직렬화되어 세션에 저장
            session.setAttribute("member",
                    MemberDTO.builder().id(foundMember.getId())
                            .memberPassword(foundMember.getMemberPassword())
                            .memberEmail(foundMember.getMemberEmail())
                            .memberAddress(foundMember.getMemberAddress())
                            .memberAge(foundMember.getMemberAge())
                            .memberName(foundMember.getMemberName())
                            .memberInterest(foundMember.getMemberInterest())
                            .memberJoinType(foundMember.getMemberJoinType())
                            .memberRice(foundMember.getMemberRice())
                            .memberRank(foundMember.getMemberRank())
                            .memberRole(foundMember.getMemberRole())
                            .memberStatus(foundMember.getMemberStatus())
                            .memberVolunteerTime(foundMember.getMemberVolunteerTime())
                            .randomKey(foundMember.getRandomKey())
                            .build()
            );
            /*memberRepository.save(foundMember);*/

        } else {
//          첫 오어스 로그인 시 진입
//          OAuth를 통해 전달받은 정보를 DTO로 변환하여 session에 저장
//          session에 객체를 저장하기 위해 직렬화 사용(다시 가져올 때에는 역직렬화를 통해 원본 객체 생성)
//          회원 번호를 사용하는 것 보다 OAuth 인증에 작성된 이메일을 사용하는 것이 올바르다.

//            필요한 정보를 폼 페이지에 미리 채워 넣기 위해 해당 정보를 세션에 저장

//            이전에 생성된 Member 객체가 존재한다면 해당 객체를 업데이트하고, 존재하지 않는다면 새로운 Member 객체를 생성
            foundMember = foundMemberOptional.map(member -> member.update(attributes.getEmail(), attributes.getMemberJoinType()))
                    .orElse(attributes.toEntity());
//          세션에 저장할 MemberDTO 객체를 생성하고 OAuth를 통해 전달받은 정보를 DTO로 변환하여 session에 저장
            session.setAttribute("member", MemberDTO.builder().memberEmail(attributes.getEmail()).memberJoinType(attributes.getMemberJoinType()).build());
//          객체를 세션에서 가져올 때 역직렬화를 수행하여 원본 객체를 생성 이를 위해 session.getAttribute("member")를 사용하여 세션에서 객체를 가져옴
            MemberDTO sessionMemberDTO = (MemberDTO) session.getAttribute("member");
            log.info(sessionMemberDTO + "첫 오어스 로그인 시 진입");
            log.info(foundMember + "첫 오어스 로그인 시 진입 foundMember");
        }

        return foundMember;

    }
}
