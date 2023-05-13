package com.app.simbongsa.service.rice;

import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.domain.RicePaymentDTO;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.entity.rice.RicePayment;
import com.app.simbongsa.search.admin.AdminPaymentSearch;
import com.app.simbongsa.type.RicePaymentType;
import org.springframework.data.domain.Page;

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

    default RicePaymentDTO toRicePaymentDTO(RicePayment ricePayment) {
        return RicePaymentDTO.builder()
                .id(ricePayment.getId())
                .ricePaymentUsed(ricePayment.getRicePaymentUsed())
                .ricePaymentExchangeBank(ricePayment.getRicePaymentExchangeBank())
                .ricePaymentExchangeAccount(ricePayment.getRicePaymentExchangeAccount())
                .ricePaymentStatus(ricePayment.getRicePaymentStatus())
                .memberDTO(toMemberDTO(ricePayment.getMember()))
                .createdDate(ricePayment.getCreatedDate())
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
