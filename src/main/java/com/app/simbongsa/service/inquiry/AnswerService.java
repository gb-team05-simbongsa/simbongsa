package com.app.simbongsa.service.inquiry;

import com.app.simbongsa.domain.AnswerDTO;
import com.app.simbongsa.domain.InquiryDTO;
import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.entity.inquiry.Answer;
import com.app.simbongsa.entity.inquiry.Inquiry;
import com.app.simbongsa.entity.member.Member;

public interface AnswerService {
    //    답변 작성
    public void saveAnswer(AnswerDTO answerDTO);

    default Answer toAnswerEntity(AnswerDTO answerDTO) {
        return Answer.builder()
                .id(answerDTO.getId())
                .answerTitle(answerDTO.getAnswerTitle())
                .answerContent(answerDTO.getAnswerContent())
                .inquiry(toInquiryEntity(answerDTO.getInquiryDTO()))
                .build();
    }

    default Inquiry toInquiryEntity(InquiryDTO inquiryDTO) {
        return Inquiry.builder()
                .id(inquiryDTO.getId())
                .inquiryTitle(inquiryDTO.getInquiryTitle())
                .inquiryContent(inquiryDTO.getInquiryContent())
                .inquiryStatus(inquiryDTO.getInquiryStatus())
                .member(toMemberEntity(inquiryDTO.getMemberDTO()))
                .build();
    }

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
