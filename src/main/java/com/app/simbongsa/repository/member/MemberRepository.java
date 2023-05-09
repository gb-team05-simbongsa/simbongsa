package com.app.simbongsa.repository.member;

import com.app.simbongsa.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryDsl {
}
