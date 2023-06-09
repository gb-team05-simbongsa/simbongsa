package com.app.simbongsa.service.volunteer;

import com.app.simbongsa.domain.*;
import com.app.simbongsa.entity.file.File;
import com.app.simbongsa.entity.file.VolunteerWorkFile;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.entity.volunteer.VolunteerWorkActivity;
import com.app.simbongsa.search.admin.AdminBoardSearch;
import com.app.simbongsa.search.admin.AdminVolunteerSearch;
import com.app.simbongsa.type.VolunteerWorkCategoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface VolunteerWorkService {
    // 1. 메인페이지 - 봉사 활동 8개 TEST
    public List<VolunteerWorkDTO> getVolunteerList();

//    현재 시퀀스 가져오기
    public VolunteerWork getCurrentSequence();

//    봉사 전체 조회
    public Page<VolunteerWorkDTO> getVolunteerWork(Integer page, AdminVolunteerSearch adminVolunteerSearch);

//    봉사 등록
    public void saveVolunteerWork(VolunteerWorkDTO volunteerWorkDTO);

//    봉사 상세보기
    public VolunteerWorkDTO getVolunteerWorkDetail(Long id);

//    봉사 삭제
    public void deleteVolunteerWorkByIds(List<Long> ids);

//    봉사 수정
    public void updateVolunteerWork(VolunteerWorkDTO volunteerWorkDTO);

//    ** 봉사 목록 지역 별 조회
    public Page<VolunteerWorkDTO> pagingVolunteerWork(String keyword, Integer page, String volunteerWorkCategoryType);
//    ** 봉사 목록 카테고리별 조회
    public Page<VolunteerWorkDTO> pagingVolunteerWorkCategory(VolunteerWorkCategoryType volunteerWorkCategoryType, Integer page);


    default VolunteerWorkDTO toVolunteerWorkDTO(VolunteerWork volunteerWork){
        VolunteerWorkDTO.VolunteerWorkDTOBuilder builder = VolunteerWorkDTO.builder()
                .id(volunteerWork.getId())
                .volunteerWorkTitle(volunteerWork.getVolunteerWorkTitle())
                .volunteerWorkContent(volunteerWork.getVolunteerWorkContent())
                .volunteerWorkPlace(volunteerWork.getVolunteerWorkPlace())
                .volunteerWorkCategory(volunteerWork.getVolunteerWorkCategory())
                .volunteerWorkEndDate(volunteerWork.getVolunteerWorkEndDate())
                .volunteerWorkJoinEndDate(volunteerWork.getVolunteerWorkJoinEndDate())
                .volunteerWorkJoinStartDate(volunteerWork.getVolunteerWorkJoinStartDate())
                .volunteerWorkRecruitNumber(volunteerWork.getVolunteerWorkRecruitNumber())
                .volunteerWorkRegisterAgency(volunteerWork.getVolunteerWorkRegisterAgency())
                .volunteerWorkStartDate(volunteerWork.getVolunteerWorkStartDate())
                .volunteerWorkTime(volunteerWork.getVolunteerWorkTime())
                .volunteerWorkActivityDTOS(toVolunteerActivityDTO(volunteerWork.getVolunteerWorkActivities()));

        if (volunteerWork.getVolunteerWorkFile() != null) {
            builder.fileDTO(toFileDTO(volunteerWork.getVolunteerWorkFile()));
        }

        return builder.build();
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

    default FileDTO toFileDTO(VolunteerWorkFile volunteerWorkFile) {
        return FileDTO.builder()
                .id(volunteerWorkFile.getId())
                .fileName(volunteerWorkFile.getFileName())
                .filePath(volunteerWorkFile.getFilePath())
                .fileUuid(volunteerWorkFile.getFileUuid())
                .fileRepresentationalType(volunteerWorkFile.getFileRepresentationalType())
                .build();
    }

    default List<FileDTO> FileToDTO(List<VolunteerWorkFile> volunteerWorkFiles){
        List<FileDTO> volunteerFileList = new ArrayList<>();
        volunteerWorkFiles.forEach(
                volunteerFile->{
                    FileDTO fileDTO = FileDTO.builder()
                            .id(volunteerFile.getId())
                            .fileName(volunteerFile.getFileName())
                            .fileUuid(volunteerFile.getFileUuid())
                            .fileRepresentationalType(volunteerFile.getFileRepresentationalType())
                            .filePath(volunteerFile.getFilePath())
                            .build();
                    volunteerFileList.add(fileDTO);
                } );
        return volunteerFileList;
    }

    default List<VolunteerWorkActivityDTO> toVolunteerActivityDTO(List<VolunteerWorkActivity> volunteerWorkActivities){
        List<VolunteerWorkActivityDTO> volunteerWorkActivityList = new ArrayList<>();
        volunteerWorkActivities.forEach(
                volunteerWorkActivity->{
                    VolunteerWorkActivityDTO volunteerWorkActivityDTO = VolunteerWorkActivityDTO.builder()
                            .id(volunteerWorkActivity.getId())
                            .volunteerWorkActivityDate(volunteerWorkActivity.getVolunteerWorkActivityDate())
                            .memberDTO(toMemberDTO(volunteerWorkActivity.getMember()))
                            .build();
                    volunteerWorkActivityList.add(volunteerWorkActivityDTO);
                } );
        return volunteerWorkActivityList;
    }

    default VolunteerWork toVolunteerWorkEntity(VolunteerWorkDTO volunteerWorkDTO){
        VolunteerWork.VolunteerWorkBuilder builder = VolunteerWork.builder()
                .volunteerWorkCategory(volunteerWorkDTO.getVolunteerWorkCategory())
                .volunteerWorkEndDate(volunteerWorkDTO.getVolunteerWorkEndDate())
                .volunteerWorkJoinEndDate(volunteerWorkDTO.getVolunteerWorkJoinEndDate())
                .volunteerWorkJoinStartDate(volunteerWorkDTO.getVolunteerWorkJoinStartDate())
                .volunteerWorkFile(toVolunteerWorkFileEntity(volunteerWorkDTO.getFileDTO()))
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

        return builder.build();
    }

    default VolunteerWorkFile toVolunteerWorkFileEntity(FileDTO fileDTO) {
        VolunteerWorkFile.VolunteerWorkFileBuilder builder = VolunteerWorkFile.builder()
                .fileName(fileDTO.getFileName())
                .filePath(fileDTO.getFilePath())
                .fileUuid(fileDTO.getFileUuid())
                .fileRepresentationalType(fileDTO.getFileRepresentationalType())
                .volunteerWork(fileDTO.getVolunteerWork());

        if (fileDTO.getId() != null) {
            builder.id(fileDTO.getId());
        }

        return builder.build();
    }
}
