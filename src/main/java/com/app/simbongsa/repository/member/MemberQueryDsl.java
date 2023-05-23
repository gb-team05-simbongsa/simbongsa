package com.app.simbongsa.repository.member;

import com.app.simbongsa.search.admin.AdminMemberSearch;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.type.MemberStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public interface MemberQueryDsl {

//    회원 전체 조회(페이징)
    public Page<Member> findAllWithPaging(AdminMemberSearch adminMemberSearch, Pageable pageable);

//    회원 정보 수정
    public void updateMember(Long id, String memberName, String memberAddress, String memberInterest, int memberVolunteerTime);

//   회원 랭킹 조회
    public List<Member> findMemberWithVolunteerTime();

    /* 이메일 로그인 */
    public Optional<Member> login(String memberEmail, String memberPassword);

    //    비밀 번호 찾기
    public Optional<Member> findByMemberEmailForPassword(String memberEmail);

    //    비밀 번호 변경
    public void updatePassword(String memberEmail, String memberPassword);

//    후원 명단 조회
    public List<Member> findSupportByRequestId(Long id);

    /*이메일 중복 검사*/
    public Long overlapByMemberEmail(String memberEmail);

    /* 내 공양미 환전 요청*/
    public void updateChangeRiceByMemberId(Long memberId, int changeRice);

    /* 공양미 충전 */
    public void updateChargeRiceByMemberId(Long memberId, int chargeRice);

    /*마이페이지 회원 정보 수정*/
    public void updateMyPageMember(Long id, String memberPassword, String memberName, String memberAddress, int memberAge, String memberInterest, PasswordEncoder passwordEncoder);

    /*회원 탈퇴*/
    public void updateMemberStatus(Long id, MemberStatus memberStatus);

    /* 랜덤키 값 변경 */
    public void updateRandomKey(String memberEmail, String randomKey);

    public void updateMemberCash(Member memberModify);

    /* 메인 이메일 찾기 */
    public Member findByMemberEmail_QueryDSL(String memberEmail);

    /* 회원정보수정 비밀번호 */
    public void memberUpdatePassword_QueryDSL(String memberEmail, String memberPassword);

    /* 회원정보수정 이름 */
    public void memberUpdateName_QueryDSL(String memberEmail, String memberName);

    /* 회원정보수정 주소 */
    public void memberUpdateAddress_QueryDSL(String memberEmail, String memberAddress);
}
