package com.app.simbongsa.service.volunteer;

import com.app.simbongsa.domain.*;
import com.app.simbongsa.entity.file.VolunteerWorkFile;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.entity.support.Support;
import com.app.simbongsa.entity.support.SupportRequest;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.entity.volunteer.VolunteerWorkActivity;
import com.app.simbongsa.provider.UserDetail;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface VolunteerWorkActivityService {
    public Page<VolunteerWorkActivityDTO> getVolunteerWorkActivity(Long id, Integer page);

    /* 내 봉사 활동 목록 조회(페이징처리) */
    public Page<VolunteerWorkActivityDTO> getMyVolunteerWork(Integer page, @AuthenticationPrincipal UserDetail userDetail);

    /* 봉사활동 참가 모달*/
    public void saveVolunteerWorkActivity(VolunteerWorkActivityDTO volunteerWorkActivityDTO);

    default VolunteerWorkActivityDTO toVolunteerWorkActivityDTO(VolunteerWorkActivity volunteerWorkActivity) {
        return VolunteerWorkActivityDTO.builder()
                .id(volunteerWorkActivity.getId())
                .memberDTO(toMemberDTO(volunteerWorkActivity.getMember()))
                .volunteerWorkActivityDate(volunteerWorkActivity.getVolunteerWorkActivityDate())
                .volunteerWorkDTO(toVolunteerWorkDTO(volunteerWorkActivity.getVolunteerWork()))
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

    default VolunteerWorkDTO toVolunteerWorkDTO(VolunteerWork volunteerWork){
        return VolunteerWorkDTO.builder()
                .volunteerWorkTitle(volunteerWork.getVolunteerWorkTitle())
                .volunteerWorkPlace(volunteerWork.getVolunteerWorkPlace())
                .volunteerWorkCategory(volunteerWork.getVolunteerWorkCategory())
                .id(volunteerWork.getId())
                .volunteerWorkEndDate(volunteerWork.getVolunteerWorkEndDate())
                .fileDTO(toFileDTO(volunteerWork.getVolunteerWorkFile()))
                .volunteerWorkJoinEndDate(volunteerWork.getVolunteerWorkJoinEndDate())
                .volunteerWorkJoinStartDate(volunteerWork.getVolunteerWorkJoinStartDate())
                .volunteerWorkRecruitNumber(volunteerWork.getVolunteerWorkRecruitNumber())
                .volunteerWorkRegisterAgency(volunteerWork.getVolunteerWorkRegisterAgency())
                .volunteerWorkStartDate(volunteerWork.getVolunteerWorkStartDate())
                .volunteerWorkTime(volunteerWork.getVolunteerWorkTime())
                .build();
    }


    default FileDTO toFileDTO(VolunteerWorkFile volunteerWorkFile) {
        return FileDTO.builder()
                .fileName(volunteerWorkFile.getFileName())
                .filePath(volunteerWorkFile.getFilePath())
                .fileUuid(volunteerWorkFile.getFileUuid())
                .fileRepresentationalType(volunteerWorkFile.getFileRepresentationalType())
                .build();
    }

    default VolunteerWorkActivity toVolunteerWorkActivityEntity(VolunteerWorkActivityDTO volunteerWorkActivityDTO){
        return VolunteerWorkActivity.builder()
                .id(volunteerWorkActivityDTO.getId())
                .volunteerWork(toVolunteerWorkEntity(volunteerWorkActivityDTO.getVolunteerWorkDTO()))
                .volunteerWorkActivityDate(volunteerWorkActivityDTO.getVolunteerWorkActivityDate())
                .member(toMemberEntity(volunteerWorkActivityDTO.getMemberDTO()))
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
    default VolunteerWork toVolunteerWorkEntity(VolunteerWorkDTO volunteerWorkDTO){
        VolunteerWork.VolunteerWorkBuilder builder = VolunteerWork.builder()
                .volunteerWorkEndDate(volunteerWorkDTO.getVolunteerWorkEndDate())
                .volunteerWorkJoinEndDate(volunteerWorkDTO.getVolunteerWorkJoinEndDate())
                .volunteerWorkJoinStartDate(volunteerWorkDTO.getVolunteerWorkJoinStartDate())
                .volunteerWorkPlace(volunteerWorkDTO.getVolunteerWorkPlace())
                .volunteerWorkRecruitNumber(volunteerWorkDTO.getVolunteerWorkRecruitNumber())
                .volunteerWorkRegisterAgency(volunteerWorkDTO.getVolunteerWorkRegisterAgency())
                .volunteerWorkStartDate(volunteerWorkDTO.getVolunteerWorkStartDate())
                .volunteerWorkTime(volunteerWorkDTO.getVolunteerWorkTime())
                .volunteerWorkTitle(volunteerWorkDTO.getVolunteerWorkTitle())
                .volunteerWorkContent(volunteerWorkDTO.getVolunteerWorkContent());

        if (volunteerWorkDTO.getId() != null) {
            builder.id(volunteerWorkDTO.getId());
        }
        if (volunteerWorkDTO.getVolunteerWorkCategory() != null) {
            builder.volunteerWorkCategory(volunteerWorkDTO.getVolunteerWorkCategory());
        }

        return builder.build();
    }
}
