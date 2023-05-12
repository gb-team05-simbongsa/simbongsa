package com.app.simbongsa.service.inquiry;

import com.app.simbongsa.domain.AnswerDTO;
import com.app.simbongsa.domain.InquiryDTO;
import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.domain.NoticeDTO;
import com.app.simbongsa.entity.inquiry.Inquiry;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.search.admin.AdminBoardSearch;
import com.app.simbongsa.search.admin.AdminNoticeSearch;
import org.springframework.data.domain.Page;

public interface InquiryService {
//    문의 목록 조회
    public Page<InquiryDTO> getInquiry(Integer page, AdminBoardSearch adminBoardSearch);

//    문의 상세보기
    public InquiryDTO getInquiryDetail(Long id);

//    문의 삭제
    public void deleteInquiry(Long id);

//    문의 검색

    default InquiryDTO toInquiryDTO(Inquiry inquiry) {
        return InquiryDTO.builder()
                .id(inquiry.getId())
                .inquiryTitle(inquiry.getInquiryTitle())
                .inquiryContent(inquiry.getInquiryContent())
                .inquiryStatus(inquiry.getInquiryStatus())
                .memberDTO(toMemberDTO(inquiry.getMember()))
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
}
