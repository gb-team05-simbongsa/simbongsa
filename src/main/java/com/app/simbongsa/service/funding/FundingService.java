package com.app.simbongsa.service.funding;

import com.app.simbongsa.domain.*;
import com.app.simbongsa.entity.file.File;
import com.app.simbongsa.entity.file.FundingFile;
import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.entity.funding.FundingCreator;
import com.app.simbongsa.entity.funding.FundingPayment;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.search.admin.AdminFundingSearch;
import com.app.simbongsa.type.FundingCategoryType;
import com.app.simbongsa.type.RequestType;
import org.hibernate.type.StringNVarcharType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface FundingService {
    // 메인페이지 - 인기펀딩
    public List<FundingDTO> getAllPopularFundingList();

    // 펀딩 저장
    public void fundingRegister(FundingDTO fundingDTO, Long memberId);


    //펀딩 전체 목록 조회
    public Slice<FundingDTO> getFundingList(Pageable pageable);

    //펀딩 전체 갯수
    public Integer getFundingCount(Long fundingId);

    // 펀딩 상세보기
    public FundingDTO getFundingDetail(Long fundingId);

//    펀딩 전체 목록 조회
    public Page<FundingDTO> getFunding(Integer page, AdminFundingSearch adminFundingSearch);

//    펀딩 삭제
    public void deleteFunding(List<Long> ids);

//    펀딩 승인
    public void updateFundingStatus(List<Long> ids, RequestType requestType);

//    펀딩 승인, 대기 수 조회
    public List<Long> countAcceptAndWait();

// 펀딩 계획 등록
    public void updateFundingPlan(Long fundingId, int fundingTargetPrice, LocalDate fundingStartDate, LocalDate fundingEndDate);

    // 현재 시퀀스 가져오기
    public Funding getCurrentSequence();

    // 내가 만든 펀딩 목록(페이징처리)
    public Page<FundingDTO> getMyFunding(Integer page, MemberDTO memberDTO);

//    내가 후원한 펀딩 목록(페이징)
    public Page<FundingPaymentDTO> getFundingSupportByMemberId(Integer page, Long id);


    default FundingPaymentDTO toFundingPaymentDTO(FundingPayment fundingPayment) {
        return FundingPaymentDTO.builder()
                .id(fundingPayment.getId())
                .fundingPaymentDate(fundingPayment.getFundingPaymentDate())
                .fundingPaymentPrice(fundingPayment.getFundingPaymentPrice())
                .memberDTO(toMemberDTO(fundingPayment.getMember()))
                .fundingDTO(toFundingDTO(fundingPayment.getFunding()))
                .build();
    }

//    default FundingDTO toFundingDTO(Funding funding) {
//        return FundingDTO.builder()
//                .fundingCategory(funding.getFundingCategory())
//                .fundingCurrentPrice(funding.getFundingCurrentPrice())
//                .fundingTargetPrice(funding.getFundingTargetPrice())
//                .fundingTitle(funding.getFundingTitle())
//                .fundingCreator(funding.getFundingCreator())
//                .fundingPercent((int)((double)funding.getFundingCurrentPrice() / funding.getFundingTargetPrice() * 100))
//                .build();
//    }

    default FundingDTO toFundingDTO(Funding funding) {
        return FundingDTO.builder()
                .id(funding.getId())
                .fundingCategory(funding.getFundingCategory())
                .fundingTitle(funding.getFundingTitle())
                .fundingShortTitle(funding.getFundingShortTitle())
                .fundingSummary(funding.getFundingSummary())
                .fundingTargetPrice(funding.getFundingTargetPrice())
                .fundingCurrentPrice(funding.getFundingCurrentPrice())
                .fundingStartDate(funding.getFundingStartDate())
                .fundingEndDate(funding.getFundingEndDate())
                .fundingIntroduce(funding.getFundingIntroduce())
                .fundingBudgetExplain(funding.getFundingBudgetExplain())
                .fundingScheduleExplain(funding.getFundingScheduleExplain())
                .fundingGiftExplain(funding.getFundingGiftExplain())
                .fundingStatus(funding.getFundingStatus())
                .fundingCreator(funding.getFundingCreator())
                .fileDTOs(FileToDTO(funding.getFundingFile()))
                .build();
    }
    default MemberDTO toMemberDTO(Member member){
        return MemberDTO.builder()
                .id(member.getId())
                .memberName(member.getMemberName())
                .memberEmail(member.getMemberEmail())
                .memberPassword(member.getMemberPassword())
                .memberAddress(member.getMemberAddress())
                .memberAge(member.getMemberAge())
                .memberInterest(member.getMemberInterest())
                .memberRole(member.getMemberRole())
                .memberJoinType(member.getMemberJoinType())
                .memberRank(member.getMemberRank())
                .memberRice(member.getMemberRice())
                .memberVolunteerTime(member.getMemberVolunteerTime())
                .randomKey(member.getRandomKey())
                .memberStatus(member.getMemberStatus())
                .build();
    }
    default List<FileDTO> FileToDTO(List<FundingFile> fundingFiles){
        List<FileDTO> fundingFileList = new ArrayList<>();
        fundingFiles.forEach(
                fundingFile -> {
                    FileDTO fileDTO = FileDTO.builder()
                            .id(fundingFile.getId())
                            .fileName(fundingFile.getFileName())
                            .filePath(fundingFile.getFilePath())
                            .fileRepresentationalType(fundingFile.getFileRepresentationalType())
                            .fileUuid(fundingFile.getFileUuid())
                            .build();
                    fundingFileList.add(fileDTO);
                }
        );
        return fundingFileList;
    }

  /*  default Funding toFundingEntity(FundingDTO fundingDTO) {
        return Funding.builder()
                .id(fundingDTO.getId())
                .fundingCategory(fundingDTO.getFundingCategory())
                .fundingTitle(fundingDTO.getFundingTitle())
                .fundingShortTitle(fundingDTO.getFundingShortTitle())
                .fundingSummary(fundingDTO.getFundingSummary())
                .fundingBudgetExplain(fundingDTO.getFundingBudgetExplain())
                .fundingIntroduce(fundingDTO.getFundingIntroduce())
                .fundingScheduleExplain(fundingDTO.getFundingScheduleExplain())
                .fundingGiftExplain(fundingDTO.getFundingGiftExplain())
                .build();

    }*/

    default Funding toFundingEntity(FundingDTO fundingDTO) {
        Funding.FundingBuilder builder = Funding.builder()
                .id(fundingDTO.getId())
                .fundingCategory(fundingDTO.getFundingCategory())
                .fundingTitle(fundingDTO.getFundingTitle())
                .fundingShortTitle(fundingDTO.getFundingShortTitle())
                .fundingSummary(fundingDTO.getFundingSummary())
                .fundingBudgetExplain(fundingDTO.getFundingBudgetExplain())
                .fundingIntroduce(fundingDTO.getFundingIntroduce())
                .fundingScheduleExplain(fundingDTO.getFundingScheduleExplain())
                .fundingGiftExplain(fundingDTO.getFundingGiftExplain())
                .fundingStatus(fundingDTO.getFundingStatus())
                .fundingCreator(fundingDTO.getFundingCreator())
                .fundingFile(toFundingFileListEntity(fundingDTO.getFileDTOs()))
                .member(toMemberEntity(fundingDTO.getMemberDTO()));


        if (fundingDTO.getId() != null) {
            builder.id(fundingDTO.getId());
        }

        return builder.build();

    }



    default Member toMemberEntity(MemberDTO memberDTO){
        return Member.builder()
                .id(memberDTO.getId())
                .memberEmail(memberDTO.getMemberEmail())
                .memberAddress(memberDTO.getMemberAddress())
                .memberAge(memberDTO.getMemberAge())
                .memberInterest(memberDTO.getMemberInterest())
                .memberJoinType(memberDTO.getMemberJoinType())
                .memberName(memberDTO.getMemberName())
                .memberPassword(memberDTO.getMemberPassword())
                .memberRank(memberDTO.getMemberRank())
                .memberRice(memberDTO.getMemberRice())
                .memberRole(memberDTO.getMemberRole())
                .memberStatus(memberDTO.getMemberStatus())
                .memberVolunteerTime(memberDTO.getMemberVolunteerTime())
                .randomKey(memberDTO.getRandomKey())
                .build();
    }

    default FundingCreator toFundingCreatorEntity(FundingCreator fundingCreator) {
        return FundingCreator.builder()
                .fundingCreatorNickname(fundingCreator.getFundingCreatorNickname())
                .fundingCreatorIntroduce(fundingCreator.getFundingCreatorIntroduce())
                .build();
    }

    default FundingFile toFundingFileEntity(FileDTO fileDTO){
        return FundingFile.builder()
                .fileName(fileDTO.getFileName())
                .filePath(fileDTO.getFilePath())
                .fileRepresentationalType(fileDTO.getFileRepresentationalType())
                .fileUuid(fileDTO.getFileUuid())
                .funding(fileDTO.getFunding())
                .build();
    }

    default List<FundingFile> toFundingFileListEntity(List<FileDTO> fileDTOS){
        List<FundingFile> fundingFiles = new ArrayList<>();

        fileDTOS.forEach(
                fileDTO ->{
                    FundingFile fundingFile = FundingFile.builder()
                            .fileName(fileDTO.getFileName())
                            .filePath(fileDTO.getFilePath())
                            .fileRepresentationalType(fileDTO.getFileRepresentationalType())
                            .fileUuid(fileDTO.getFileUuid())
                            .build();
                    fundingFiles.add(fundingFile);
                }
        );
        return fundingFiles;
    }


}
