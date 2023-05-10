package com.app.simbongsa.repository.member;

import com.app.simbongsa.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryDsl {
    //    아이디로 전체 정보 조회 (MemberDetailService)
    public Optional<Member> findByMemberEmail(String memberEmail);
}
