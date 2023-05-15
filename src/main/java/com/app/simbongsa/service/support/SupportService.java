package com.app.simbongsa.service.support;

import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.domain.SupportDTO;
import com.app.simbongsa.domain.SupportRequestDTO;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.entity.support.Support;
import com.app.simbongsa.entity.support.SupportRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SupportService {
    public Page<SupportDTO> getAllSupportAttendWithMember_QueryDSL(Integer page, Long id);
    public Long getAllSupportAttend_QueryDSL(Long id);

//    후원명단 조회
    public List<SupportDTO> getSupportList(Long id);


    default SupportDTO toSupportDTO(Support support){
        return SupportDTO.builder()
                .id(support.getId())
                .memberDTO(toMemberDTO(support.getMember()))
                .supportPrice(support.getSupportPrice())
                .supportRequestDTO(toSupportRequestDTO(support.getSupportRequest()))
                .build();
    }

    default MemberDTO toMemberDTO(Member member){
        return MemberDTO.builder()
                .id(member.getId())
                .memberRank(member.getMemberRank())
                .memberName(member.getMemberName())
                .memberVolunteerTime(member.getMemberVolunteerTime())
                .memberAddress(member.getMemberAddress())
                .memberEmail(member.getMemberEmail())
                .memberAge(member.getMemberAge())
                .memberPassword(member.getMemberPassword())
                .memberInterest(member.getMemberInterest())
                .memberJoinType(member.getMemberJoinType())
                .memberRice(member.getMemberRice())
                .memberRole(member.getMemberRole())
                .memberStatus(member.getMemberStatus())
                .build();
    }
    default SupportRequestDTO toSupportRequestDTO(SupportRequest supportRequest) {
        return SupportRequestDTO.builder()
                .id(supportRequest.getId())
                .supportRequestTitle(supportRequest.getSupportRequestTitle())
                .supportRequestContent(supportRequest.getSupportRequestContent())
                .supportRequestStatus(supportRequest.getSupportRequestStatus())
                .createdDate(supportRequest.getCreatedDate())
                .updatedDate(supportRequest.getUpdatedDate())
                .memberDTO(toMemberDTO(supportRequest.getMember()))
                .supports(supportRequest.getSupports())
                .supportRequestFiles(supportRequest.getSupportRequestFiles())
                .build();
    }
}
