package com.app.simbongsa.service.member;

import com.app.simbongsa.domain.MailDTO;
import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.domain.VolunteerWorkDTO;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.search.admin.AdminMemberSearch;
import com.app.simbongsa.type.MemberJoinType;
import com.app.simbongsa.type.MemberStatus;
import com.app.simbongsa.type.Role;
import com.app.simbongsa.type.UserRankType;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public interface MemberService extends UserDetailsService {
    //    회원가입
    public void join(MemberDTO memberDTO, PasswordEncoder passwordEncoder);
    //    메인페이지
    public List<MemberDTO> getMemberRankingList();

//    회원 전체 조회
    public Page<MemberDTO> getMembers(Integer page, AdminMemberSearch adminMemberSearch);

//    회원 상세보기
    public MemberDTO getMemberById(Long id);

    public MemberDTO getMemberByEmail(String memberEmail);

//    회원 탈퇴(관리자)
    public void updateStatusByIds(List<Long> ids);

//    후원 명단 조회
    public List<Member> getSupportList(Long id);

    /* 카카오 토큰 접근 */
    public String getKaKaoAccessToken(String code, String type);

    /* 카카오 사용자 정보 불러오기 */
    public MemberDTO getKakaoInfo(String token) throws Exception;

    /*이메일 중복 검사*/
    public Long overlapByMemberEmail(String memberEmail);
    
    /* 랜덤키 생성 */
    public String randomKey();

    /* 랜덤키 변경 */
    public void updateRandomKey(String memberEmail, String randomKey);

    /* 메일보내기 */
    public void sendMail(MailDTO mail);

    /* 비밀번호변경 */
    public void updatePassword(String memberEmail, String memberPassword);
    public void updateMemberRice(MemberDTO memberDTO);

    /**/
    public void updatePasswordAndResetRandomKey(String memberEmail, String memberPassword, PasswordEncoder passwordEncoder);

    /* 메인에서 쓰는 이메일찾기 */
    public Member findByMemberEmail(String memberEmail);

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
}
