package com.app.simbongsa.repository.member;

import com.app.simbongsa.search.admin.AdminMemberSearch;
import com.app.simbongsa.entity.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MemberQueryDsl {

//    회원 전체 조회(페이징)
    public Page<Member> findAllWithPaging(AdminMemberSearch adminMemberSearch, Pageable pageable);

//    회원 정보 수정
    public void updateMember(Long id, String memberName, String memberAddress, String memberInterest, int memberVolunteerTime);

//   회원 랭킹 조회
    public List<Member> findMemberWithVolunteerTime();

    //    비밀 번호 찾기
    public Optional<Member> findByMemberEmailForPassword(String memberEmail);

    //    비밀 번호 변경
    public void updatePassword(Long id, String memberPassword);
}
