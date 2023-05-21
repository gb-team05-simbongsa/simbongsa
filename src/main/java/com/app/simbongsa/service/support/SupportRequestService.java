package com.app.simbongsa.service.support;

import com.app.simbongsa.domain.*;
import com.app.simbongsa.entity.file.File;
import com.app.simbongsa.entity.file.SupportRequestFile;
import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.domain.SupportRequestDTO;
import com.app.simbongsa.entity.file.VolunteerWorkFile;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.entity.support.Support;
import com.app.simbongsa.entity.support.SupportRequest;
import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.search.admin.AdminSupportRequestSearch;
import com.app.simbongsa.summernote.SupportRequestFileDTO;
import com.app.simbongsa.type.MemberStatus;
import com.app.simbongsa.type.RequestType;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public interface SupportRequestService {

//    목록 전체 조회(페이징)
    public Page<SupportRequestDTO> getSupportRequest(Integer page, AdminSupportRequestSearch adminSupportRequestSearch);

//    후원 요청 상세보기
    public SupportRequestDTO getSupportRequestDetail(Long id);

//    후원 요청 삭제
    public void deleteSupportRequest(List<Long> ids);

//    후원 요청 승인 or 반려
    public void updateWaitToAccessOrDenied(List<Long> ids, RequestType requestType);

//    후원 목록 전체 조회(후원 목록 페이지)
    public Page<SupportRequestDTO> getSupportRequestAllWithPaging(Integer page, String keyword);

//    후원 상세페이지
//    public SupportRequestDTO getByIdWithSupportRequestInfo_QueryDsl(Long id);

    /* 내 후원요청목록 페이징처리해서 불러오기 */
    Page<SupportRequestDTO> getMySupportRequest(Integer page, UserDetail userDetail);

//    승인 대기 반려 수 조회
    public List<Long> countStatusWaitAccessDenied();

//    후원 요청 작성
  public void saveSupportRequest(SupportRequestDTO supportRequestDTO);

    default SupportRequestDTO toSupportRequestDTO(SupportRequest supportRequest) {
        return SupportRequestDTO.builder()
                .id(supportRequest.getId())
                .supportRequestTitle(supportRequest.getSupportRequestTitle())
                .supportRequestContent(supportRequest.getSupportRequestContent())
                .supportRequestStatus(supportRequest.getSupportRequestStatus())
                .createdDate(supportRequest.getCreatedDate())
                .updatedDate(supportRequest.getUpdatedDate())
                .memberDTO(toMemberDTO(supportRequest.getMember()))
                .supportDTOS(toSupportDTO(supportRequest.getSupports()))
                .fileDTOS(toFileDTO(supportRequest.getSupportRequestFiles()))
                .build();
    }

    default List<SupportDTO> toSupportDTO(List<Support> supports){
        List<SupportDTO> supportDTOList = new ArrayList<>();
        supports.forEach(
                support -> {
                    SupportDTO supportDTO = SupportDTO.builder()
                            .id(support.getId())
                            .memberDTO(toMemberDTO(support.getMember()))
                            .supportPrice(support.getSupportPrice())
                            .build();
                    supportDTOList.add(supportDTO);
                }
        );
        return supportDTOList;
    }

    default List<FileDTO> toFileDTO(List<SupportRequestFile> supportRequestFiles){
        List<FileDTO> supportRequestFileDTOList = new ArrayList<>();
        supportRequestFiles.forEach(
                supportRequestFile -> {
                    FileDTO fileDTO = FileDTO.builder()
                            .id(supportRequestFile.getId())
                            .fileName(supportRequestFile.getFileName())
                            .filePath(supportRequestFile.getFilePath())
                            .fileUuid(supportRequestFile.getFileUuid())
                            .fileRepresentationalType(supportRequestFile.getFileRepresentationalType())
                            .build();
                    supportRequestFileDTOList.add(fileDTO);
                }
        );
        return supportRequestFileDTOList;
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
    default SupportRequest toSupportRequestEntity(SupportRequestDTO supportRequestDTO){
        return SupportRequest.builder()
                .id(supportRequestDTO.getId())
                .supportRequestTitle(supportRequestDTO.getSupportRequestTitle())
                .supportRequestContent(supportRequestDTO.getSupportRequestContent())
                .supportRequestStatus(supportRequestDTO.getSupportRequestStatus())
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
    default SupportRequestFile toSupportRequestFileEntity(FileDTO fileDTO) {
        return SupportRequestFile.builder()
                .fileName(fileDTO.getFileName())
                .filePath(fileDTO.getFilePath())
                .fileUuid(fileDTO.getFileUuid())
                .fileRepresentationalType(fileDTO.getFileRepresentationalType())
                .supportRequest(fileDTO.getSupportRequest())
                .build();
    }
}
