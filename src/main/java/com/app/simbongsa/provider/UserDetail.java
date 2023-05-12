package com.app.simbongsa.provider;

import com.app.simbongsa.type.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Getter
@ToString
public class UserDetail implements UserDetails {

    private Long id;
    private String memberEmail;
    private String memberPassword;
    private Role memberRole;
    private Collection<? extends GrantedAuthority> authorities;

    @Builder
    public UserDetail(Long id, String memberEmail, String memberPassword, Role memberRole, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberRole = memberRole;
        this.authorities = AuthorityUtils.createAuthorityList(memberRole.getSecurityRole());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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

    public UserDetail() {
    }
}
