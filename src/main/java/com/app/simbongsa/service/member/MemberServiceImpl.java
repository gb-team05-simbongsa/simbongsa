package com.app.simbongsa.service.member;

import com.app.simbongsa.domain.InquiryDTO;
import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.entity.inquiry.Inquiry;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.repository.member.MemberRepository;
import com.app.simbongsa.search.admin.AdminMemberSearch;
import com.app.simbongsa.type.MemberStatus;
import com.app.simbongsa.type.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("member") @Primary
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public void join(MemberDTO memberDTO, PasswordEncoder passwordEncoder) {
        memberDTO.setMemberPassword(passwordEncoder.encode(memberDTO.getMemberPassword()));
        memberDTO.setMemberRole(Role.MEMBER);
        memberRepository.save(toMemberEntity(memberDTO));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberEmail(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
        return UserDetail.builder()
                .id(member.getId())
                .memberEmail(member.getMemberEmail())
                .memberPassword(member.getMemberPassword())
                .memberRole(member.getMemberRole())
                .build();
    }

//    메인페이지 유저랭킹 목록
    @Override
    public List<MemberDTO> getMemberRankingList() {
            List<Member> members = memberRepository.findMemberWithVolunteerTime();
            List<MemberDTO> memberDTOS = new ArrayList<>();
            for(Member member : members){
                MemberDTO memberDTO = toMemberDTO(member);
                memberDTOS.add(memberDTO);
            }
        return memberDTOS;
    }

    @Override
    public Page<MemberDTO> getMembers(Integer page, AdminMemberSearch adminMemberSearch) {
        Page<Member> members = memberRepository.findAllWithPaging(adminMemberSearch, PageRequest.of(page, 5));
        List<MemberDTO> memberDTOS = members.getContent().stream().map(this::toMemberDTO).collect(Collectors.toList());
        return new PageImpl<>(memberDTOS, members.getPageable(), members.getTotalElements());
    }

    @Override
    public MemberDTO getMemberById(Long id) {
        return toMemberDTO(memberRepository.findById(id).get());
    }

    @Override
    public void updateStatusByIds(List<Long> ids, MemberStatus memberStatus) {
        ids.forEach(id -> memberRepository.updateMemberStatus(id, memberStatus));
    }

    @Override
    public List<Member> getSupportList(Long id) {
        return memberRepository.findSupportByRequestId(id);
    }


}
