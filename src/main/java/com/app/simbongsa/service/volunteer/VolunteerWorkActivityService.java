package com.app.simbongsa.service.volunteer;

import com.app.simbongsa.domain.*;
import com.app.simbongsa.entity.file.VolunteerWorkFile;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.entity.support.SupportRequest;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.entity.volunteer.VolunteerWorkActivity;
import com.app.simbongsa.provider.UserDetail;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface VolunteerWorkActivityService {
    public List<VolunteerWorkActivity> getVolunteerWorkActivity(Long id);

    /* 내 봉사 활동 목록 조회(페이징처리) */
    public Page<VolunteerWorkActivityDTO> getMyVolunteerWork(Integer page, @AuthenticationPrincipal UserDetail userDetail);

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
}
