package com.app.simbongsa.provider;

import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.type.MemberJoinType;
import com.app.simbongsa.type.MemberStatus;
import com.app.simbongsa.type.Role;
import com.app.simbongsa.type.UserRankType;
import com.sun.istack.NotNull;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Component
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class UserDetail implements UserDetails {

    private Long id;
    private String memberName;
    private String memberEmail;
    private String memberPassword;
    private String memberAddress;
    private Integer memberAge;
    private String memberInterest;
    private MemberJoinType memberJoinType;
    private UserRankType memberRank;
    private int memberRice;
    private int memberVolunteerTime;
    private String randomKey;
    private Role memberRole;
    private MemberStatus memberStatus;
    private Collection<? extends GrantedAuthority> authorities;

    @Builder
    public UserDetail(Long id, String memberName, String memberEmail, String memberPassword, String memberAddress, Integer memberAge, String memberInterest, MemberJoinType memberJoinType, UserRankType memberRank, int memberRice, int memberVolunteerTime, String randomKey, Role memberRole, MemberStatus memberStatus, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberAddress = memberAddress;
        this.memberAge = memberAge;
        this.memberInterest = memberInterest;
        this.memberJoinType = memberJoinType;
        this.memberRank = memberRank;
        this.memberRice = memberRice;
        this.memberVolunteerTime = memberVolunteerTime;
        this.randomKey = randomKey;
        this.memberRole = memberRole;
        this.memberStatus = memberStatus;
        this.authorities = authorities;
    }

    /**
     * OAuth2User 인터페이스 메소드
     */

    /**
     * UserDetails 인터페이스 메소드
     */
    // 해당 User의 권한을 리턴하는 곳!(role)
    // SecurityFilterChain에서 권한을 체크할 때 사용됨

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList(memberRole.getSecurityRole());
    }
    @Override
    public String getPassword() {
        return memberPassword;
    }

    @Override
    public String getUsername() {
        return memberEmail;
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

}
