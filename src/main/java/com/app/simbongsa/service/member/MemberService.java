package com.app.simbongsa.service.member;

import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.domain.VolunteerWorkDTO;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface MemberService extends UserDetailsService {
    //    회원가입
    public void join(MemberDTO memberDTO, PasswordEncoder passwordEncoder);
    //    메인페이지
    public List<MemberDTO> getMemberRankingList();

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

    default MemberDTO toMemberDTO(Member member){
        return MemberDTO.builder()
                .id(member.getId())
                .memberRank(member.getMemberRank())
                .memberName(member.getMemberName())
                .memberVolunteerTime(member.getMemberVolunteerTime())
                .build();
    }

}
