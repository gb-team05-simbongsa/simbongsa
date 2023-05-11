package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.search.admin.AdminFundingSearch;
import com.app.simbongsa.type.RequestType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface FundingQueryDsl {
    public List<Funding> findAllWithPopular();

    //    펀딩 전체 조회(페이징)
    public Page<Funding> findAllWithPaging(AdminFundingSearch adminFundingSearch, Pageable pageable);

    // 펀딩 후원하기
    public Funding findByIdsupport(Long fundingId);

    //    펀딩 상세페이지 조회
    public Optional<Funding> findByIdForDetail(Long fundingId);

    /* 내 펀딩 내역 조회(페이징처리) */
    /* 나중에 HttpSession session 으로 매개변수 바꿔줘야 함 */
    public Page<Funding> findByMemberId_QueryDSL(Pageable pageable, Long memberId);

//    펀딩 승인, 펀딩 대기 수 구하기
    public Long findCountAcceptOrWait(RequestType requestType);

//    펀딩 대기를 승인으로 변경
    public void updateWaitToAcceptByIds(List<Long> ids);

    // 펀딩 전체 목록조회(무한스크롤)
    public Slice<Funding> findAllWithSlice(Pageable pageable);

}