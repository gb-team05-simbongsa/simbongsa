package com.app.simbongsa.service.inquiry;

import com.app.simbongsa.domain.AnswerDTO;
import com.app.simbongsa.domain.InquiryDTO;
import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.entity.inquiry.Answer;
import com.app.simbongsa.entity.inquiry.Inquiry;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.search.admin.AdminBoardSearch;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InquiryService {
//    문의 등록
    public void saveInquiry(InquiryDTO inquiryDTO);

//    문의 목록 조회
    public Page<InquiryDTO> getInquiry(Integer page, AdminBoardSearch adminBoardSearch);

//    문의 상세보기
    public InquiryDTO getInquiryDetail(Long id);

//    문의 삭제
    public void deleteInquiry(List<Long> ids);

//    문의 검색

    /* 유저아이디로 문의 페이징처리해서 불러오기 */
    public Page<InquiryDTO> getMyInquiry(Integer page, Long id);

    /* 문의 수정 */
    public void setInquiry(InquiryDTO inquiryDTO);

    /* 문의 삭제 */
    public void deleteByInquiryId(Long id);

//    답변대기를 완료로 바꾸기
    public void updateStatusById(Long id);

//    답변 대기, 답변 완료 수 조회
    public List<Long> countStatusWaitAndComplete();

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
        return Member.builder()
                .id(memberDTO.getId())
                .memberName(memberDTO.getMemberName())
                .memberEmail(memberDTO.getMemberEmail())
                .memberPassword(memberDTO.getMemberPassword())
                .memberAddress(memberDTO.getMemberAddress())
                .memberAge(memberDTO.getMemberAge())
                .memberInterest(memberDTO.getMemberInterest())
                .memberJoinType(memberDTO.getMemberJoinType())
                .memberRank(memberDTO.getMemberRank())
                .memberRice(memberDTO.getMemberRice())
                .memberVolunteerTime(memberDTO.getMemberVolunteerTime())
                .randomKey(memberDTO.getRandomKey())
                .memberRole(memberDTO.getMemberRole())
                .memberStatus(memberDTO.getMemberStatus())
                .build();
    }

    default InquiryDTO toInquiryDTO(Inquiry inquiry) {
        InquiryDTO.InquiryDTOBuilder builder = InquiryDTO.builder()
                .id(inquiry.getId())
                .inquiryTitle(inquiry.getInquiryTitle())
                .inquiryContent(inquiry.getInquiryContent())
                .inquiryStatus(inquiry.getInquiryStatus())
                .createdDate(inquiry.getCreatedDate())
                .updatedDate(inquiry.getUpdatedDate())
                .memberDTO(toMemberDTO(inquiry.getMember()));

        if (inquiry.getAnswer() != null) {
            builder.answerDTO(toAnswerDTO(inquiry.getAnswer()));
        }

        return builder.build();
    }

    default AnswerDTO toAnswerDTO(Answer answer){
        return AnswerDTO.builder()
                .id(answer.getId())
                .answerTitle(answer.getAnswerTitle())
                .answerContent(answer.getAnswerContent())
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

    default void updateInquiry(Inquiry inquiry ,String inquiryTitle, String inquiryContent) {
        inquiry.setInquiryTitle(inquiryTitle);
        inquiry.setInquiryContent(inquiryContent);
    }
}
