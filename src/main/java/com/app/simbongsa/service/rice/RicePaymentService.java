package com.app.simbongsa.service.rice;

import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.domain.RicePaymentDTO;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.entity.rice.RicePayment;
import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.search.admin.AdminPaymentSearch;
import com.app.simbongsa.type.RicePaymentType;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface RicePaymentService {
//    공양미 충전 목록
    public Page<RicePaymentDTO> getRicePayment(Integer page, AdminPaymentSearch adminPaymentSearch, RicePaymentType ricePaymentType);

//    환전 요청 조회
    public Page<RicePaymentDTO> getRicePayment(Integer page, AdminPaymentSearch adminPaymentSearch, RicePaymentType ricePaymentFirstType, RicePaymentType ricePaymentSecondType);

//    공양미 충전 상세보기
    public RicePaymentDTO getRicePaymentDetail(Long id);

//    공양미 충전 내역 삭제
    public void deleteRicePaymentByIds(List<Long> ids);

//    공양미 환전 요청 조회

//    공양미 환전 요청 승인
    public void updatePaymentStatusToAccessByIds(List<Long> ids);

//    승인, 반려, 대기 수 조회
    public List<Long> countStatusWaitAccessDenied();

//    금일 결제 수, 결제 총 금액 조회
    public List<Long> getPaymentPriceAndPaymentCount();

    /* 내 공양미 조회(페이징) */
    public Page<RicePaymentDTO> getMyRicePayment(Integer page, MemberDTO memberDTO);

//    결제한 값 넣기
    public void insertRicePayment(Integer ricePaymentUsed, MemberDTO memberDTO);

//    환전 요청 값 넣기
    public void insertExchangeRequest(RicePaymentDTO ricePaymentDTO, MemberDTO memberDTO);

//    회원 환전 가능 공양미(후원받은 공양미) 가져오기
    public int findEnableRiceById(Long id);

    default RicePaymentDTO toRicePaymentDTO(RicePayment ricePayment) {
        RicePaymentDTO.RicePaymentDTOBuilder builder = RicePaymentDTO.builder()
                .id(ricePayment.getId())
                .ricePaymentUsed(ricePayment.getRicePaymentUsed())
                .ricePaymentStatus(ricePayment.getRicePaymentStatus())
                .memberDTO(toMemberDTO(ricePayment.getMember()))
                .createdDate(ricePayment.getCreatedDate());

        if (ricePayment.getRicePaymentExchangeBank() != null) {
            builder.ricePaymentExchangeBank(ricePayment.getRicePaymentExchangeBank());
        }

        if (ricePayment.getRicePaymentExchangeAccount() != null) {
            builder.ricePaymentExchangeAccount(ricePayment.getRicePaymentExchangeAccount());
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

    default RicePayment toRicePaymentEntity(RicePaymentDTO ricePaymentDTO) {
        RicePayment.RicePaymentBuilder builder = RicePayment.builder()
                .ricePaymentUsed(ricePaymentDTO.getRicePaymentUsed())
                .ricePaymentStatus(ricePaymentDTO.getRicePaymentStatus())
                .member(toMemberEntity(ricePaymentDTO.getMemberDTO()))
                ;

        if (ricePaymentDTO.getRicePaymentExchangeBank() != null) {
            builder.ricePaymentExchangeBank(ricePaymentDTO.getRicePaymentExchangeBank());
        }

        if (ricePaymentDTO.getRicePaymentExchangeAccount() != null) {
            builder.ricePaymentExchangeAccount(ricePaymentDTO.getRicePaymentExchangeAccount());
        }

        return builder.build();
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
