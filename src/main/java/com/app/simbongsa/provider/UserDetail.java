package com.app.simbongsa.provider;

import com.app.simbongsa.entity.member.Member;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Component
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDetail implements UserDetails, OAuth2User {

    private Member member;
    private Map<String, Object> attributes;

    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberPhoneNumber;
    private String memberName;

    /* 일반 로그인 생성자 */
    public UserDetail(Member member) {
        this.member = member;
    }

    /* OAuth 로그인 생성자 */
    public UserDetail(Member member, Map<String, Object> attributes) {
        this.member = member;
        this.attributes = attributes;
    }

    /**
     * OAuth2User 인터페이스 메소드
     */
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    /**
     * UserDetails 인터페이스 메소드
     */
    // 해당 User의 권한을 리턴하는 곳!(role)
    // SecurityFilterChain에서 권한을 체크할 때 사용됨
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {

                return String.valueOf(member.getMemberRole());
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return member.getMemberPassword();
    }

    @Override
    public String getUsername() {
        return member.getMemberName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return null;
    }

    @Builder
    public UserDetail(Long id, String memberEmail, String memberPassword, String memberPhoneNumber, String memberName) {
        this.id = id;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberPhoneNumber = memberPhoneNumber;
        this.memberName = memberName;
    }
}
