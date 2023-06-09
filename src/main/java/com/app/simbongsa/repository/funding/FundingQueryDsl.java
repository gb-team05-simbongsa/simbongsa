package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.entity.funding.FundingGift;
import com.app.simbongsa.entity.funding.FundingPayment;
import com.app.simbongsa.entity.support.Support;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.search.admin.AdminFundingSearch;
import com.app.simbongsa.type.FundingCategoryType;
import com.app.simbongsa.type.RequestType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FundingQueryDsl {
    //     메인 페이지 (인기펀딩)
    public List<Funding> findAllWithPopular();

    //    펀딩 전체 조회(페이징)
    public Page<Funding> findAllWithPaging(AdminFundingSearch adminFundingSearch, Pageable pageable);

    // 펀딩 후원하기
    public FundingGift findSupport_QueryDsl(Long giftId);

    //    펀딩 상세페이지 조회
    public Optional<Funding> findByIdForDetail_QueryDsl(Long fundingId);

    /* 내 펀딩 내역 조회(페이징처리) */
    /* 나중에 HttpSession session 으로 매개변수 바꿔줘야 함 */
    public Page<Funding> findByMemberId_QueryDSL(Pageable pageable, Long memberId);

//    펀딩 승인, 펀딩 대기 수 구하기
    public Long findCountAcceptOrWait(RequestType requestType);

//    펀딩 대기를 승인으로 변경
    public void updateWaitToAcceptByIds(List<Long> ids, RequestType requestType);

    // 펀딩 전체 목록조회(무한스크롤)
    public Slice<Funding> findAllWithSlice_QueryDsl(Pageable pageable);

    // 펀딩 전체 갯수
     public Long getFundingCount_QueryDsl(Long fundingId);

     // 펀딩 계획 등록
     public Long updateFunding_QueryDsl(Long fundingId, int fundingTargetPrice, LocalDate fundingStartDate, LocalDate fundingEndDate);

    // 현재 시퀀스 가져오기
    public Funding getCurrentSequence_QueryDsl();

//    내가 후원한 펀딩 가져오기(페이징)
    public Page<FundingPayment> findByMemberId(Pageable pageable, Long id);
}