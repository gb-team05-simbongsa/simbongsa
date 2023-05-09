package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface FundingQueryDsl {
    public List<Funding> findAllWithPopular();

    //    펀딩 전체 조회(페이징)
    public Page<Funding> findAllWithPaging(Pageable pageable);


//    // 펀딩 후원하기
//    public List<Funding> findById(Long FundingId, Long UserId);

    //    펀딩 상세페이지 조회
    public Funding findByIdForDetail(Long fundingId);

    /* 내 펀딩 내역 조회(페이징처리) */
    /* 나중에 HttpSession session 으로 매개변수 바꿔줘야 함 */
    public Page<Funding> findByUserId_QueryDSL(Pageable pageable, Long userId);

}