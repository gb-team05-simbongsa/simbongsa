package com.app.simbongsa.service.support;

import com.app.simbongsa.domain.FileDTO;
import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.domain.SupportDTO;
import com.app.simbongsa.domain.SupportRequestDTO;
import com.app.simbongsa.entity.file.SupportRequestFile;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.entity.support.Support;
import com.app.simbongsa.entity.support.SupportRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public interface SupportService {
    public Page<SupportDTO> getAllSupportAttendWithMember_QueryDSL(Integer page, Long id);
    public Long getAllSupportAttend_QueryDSL(Long id);

//    후원명단 조회
//    public List<SupportDTO> getSupportListWithPaging(Long id);


//    public List<SupportDTO> getSupportList(Long id);
  
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
}
