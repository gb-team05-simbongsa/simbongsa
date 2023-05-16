package com.app.simbongsa.repository.member;

import com.app.simbongsa.entity.inquiry.Notice;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.search.admin.AdminMemberSearch;
import com.app.simbongsa.type.MemberJoinType;
import com.app.simbongsa.type.MemberStatus;
import com.app.simbongsa.type.Role;
import com.app.simbongsa.type.UserRankType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Optional;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    //member 더미데이터 넣기
//    @Test
//    public void saveTest() {
//        for (int i = 1; i <= 100; i++) {
//            Member member = new Member( "email" + i + "@naver.com",
//                    "123" + i
//                    , "역삼로" + i
//                    , "101" +i
//                    , 20 + i
//                    ,"아무거나"
//                    , MemberJoinType.네이버
//            , UserRankType.금냥1티어
//            ,1 + i
//            ,1+i
//            , "123456789"
//            , Role.MEMBER
//            , MemberStatus.가입);
//            memberRepository.save(member);
//        }
//    }

//    회원 전체조회(페이징)
    @Test
    public void findAllWithPagingTest() {
        PageRequest pageRequest = PageRequest.of(0, 5);
        AdminMemberSearch adminMemberSearch = new AdminMemberSearch();
//        adminMemberSearch.setMemberEmail("email33");
        adminMemberSearch.setMemberAddress("삼");
        Page<Member> members = memberRepository.findAllWithPaging(adminMemberSearch, pageRequest);
        members.stream().map(Member::toString).forEach(log::info);
        log.info("=======================" + members.getTotalElements());
    }

//    회원 정보 조회(상세보기)
    @Test
    public void findByIdTest() {
        memberRepository.findById(1L).stream().map(Member::toString).forEach(log::info);
    }

//    회원 정보 수정(관리자 페이지)
    @Test
    public void updateTest() {
        memberRepository.updateMember(100L, "수정 이름", "수정 주소", "심봉사", 3);
    }

//    회원 삭제
    @Test
    public void deleteTest() {
//        memberRepository.delete(memberRepository.findById(1L).get());
        memberRepository.deleteAllById(Arrays.asList(1L, 2L, 3L));
    }

//    봉사시간 순 랭킹 조회 (메인 페이지)
    @Test
    public void findMemberWithVolunteerTime(){
        memberRepository.findMemberWithVolunteerTime().stream().map(Member::toString).forEach(log::info);
    }

//    유저 공양미 조회(후원 상세페이지 공양미 조회용)
    @Test
    public void findMemberPaymentById(){
        Optional<Member> goyangmi = memberRepository.findById(715L);
        goyangmi.ifPresent(member -> log.info("====================================" + member.getMemberRice()+ "====================="));
    }

    //    비밀번호 찾기
    @Test
    public void findByMemberEmailForPasswordTest(){
        memberRepository.findByMemberEmailForPassword("email101@naver.com").map(Member::getMemberEmail).ifPresent(log::info);
        log.info("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOooo");
    }

    //    비밀번호 변경
    @Test
    public void updatePasswordTest(){
        memberRepository.updatePassword(542L, "email101@naver.com");
    }

    /* 내 공양미 환전 요청*/
    @Test
    public void updateChangeRiceByMemberIdTest(){
        memberRepository.updateChangeRiceByMemberId(82L,300);

        memberRepository.findById(82L).ifPresent(member -> log.info("================== 공양미 남은 "+member.getMemberRice() + "==========="));
    }

    /* 공양미 충전 */
    @Test
    public void updateChargeRiceByMemberIdTest(){
        memberRepository.updateChargeRiceByMemberId(82L,300);
        memberRepository.findById(82L).ifPresent(member -> log.info("================== 7900에서 더해진 공양미 "+member.getMemberRice() + "==========="));
    }

    /* 이메일 로그인 */
    @Test
    public void loginTest(){
        Optional<Member> member1 = memberRepository.login("email82@naver.com", "12382");
        member1.ifPresent(member -> log.info(member.toString()));
    }

    /*이메일 중복 검사*/
    @Test
    public void overlapByMemberEmailTest(){
        memberRepository.overlapByMemberEmail("2678ju@naver.com");
        log.info("@@@@@@@@@@@@@@@@@@");
    }

    /* 회원정보수정 (마이페이지) */
    @Test
    public void updateMyPageTest() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberRepository.updateMyPageMember(29L, "1234","주연","강남구",22,"수정", passwordEncoder);
    }

    /*회원 탈퇴*/
    @Test
    public void updateMemberStatusTest() {
        memberRepository.updateMemberStatus(29L,MemberStatus.탈퇴);
    }

}
