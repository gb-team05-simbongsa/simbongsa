package com.app.simbongsa.service.member;

import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.entity.member.Member;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface MemberService extends UserDetailsService {
    //    회원가입
    public void join(MemberDTO memberDTO, PasswordEncoder passwordEncoder);

    default Member toMemberEntity(MemberDTO memberDTO) {
        return Member.builder().id(memberDTO.getId())
                .memberName(memberDTO.getMemberName())
                .memberEmail(memberDTO.getMemberEmail())
                .memberPassword(memberDTO.getMemberPassword())
                .memberAddress(memberDTO.getMemberAddress())
                .memberAge(memberDTO.getMemberAge())
                .memberInterest(memberDTO.getMemberInterest())
                .memberRole(memberDTO.getMemberRole())
                .memberJoinType(memberDTO.getMemberJoinType())
                .memberRank(memberDTO.getMemberRank())
                .memberRice(memberDTO.getMemberRice())
                .memberVolunteerTime(memberDTO.getMemberVolunteerTime())
                .randomKey(memberDTO.getRandomKey())
                .memberStatus(memberDTO.getMemberStatus())
                .build();
    }
}
