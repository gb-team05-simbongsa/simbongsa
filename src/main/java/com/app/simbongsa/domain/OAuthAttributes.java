package com.app.simbongsa.domain;

import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.type.MemberJoinType;
import com.app.simbongsa.type.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Getter
@Builder
@RequiredArgsConstructor
@Slf4j
public class OAuthAttributes {
    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final String email;
    //    private final MemberStatus memberStatus;
    private final MemberJoinType memberJoinType;

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        log.info(" ------------------------------ Oauth 확인 ------------------------------------------ ");
//      userNameAttributeName은 .yml에서 설정해 놓은 user-name-attribute 값이다.
        log.info("================={}", userNameAttributeName);

//      registrationId는 네이버 로그인일 경우 naver이고 카카오 로그인일 경우 kakao이다.
        log.info("================={}", registrationId);
        if("naver".equals(registrationId)) {
            return ofNaver(userNameAttributeName, attributes);
        }
        else {
            return ofKakao(userNameAttributeName, attributes);
        }
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get(userNameAttributeName);
        log.info(" ============================ 네이버 =================================== ");
        return OAuthAttributes.builder()
                .email((String) response.get("email"))
                .attributes(response)
                .nameAttributeKey("id")
                .memberJoinType(MemberJoinType.네이버)
                .build();
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String, Object>)attributes.get(userNameAttributeName);
        log.info(" ====================================== kakao ==================================== ");
        return OAuthAttributes.builder()
                .email((String) kakaoAccount.get("email"))
                .nameAttributeKey("id")
                .attributes(attributes)
                .memberJoinType(MemberJoinType.카카오)
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .memberEmail(email)
                .memberRole(Role.MEMBER)
                .memberJoinType(memberJoinType) //Naver인지 Kakao 인지
                .build();
    }
}