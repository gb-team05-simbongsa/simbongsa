package com.app.simbongsa.repository.funding;

import com.app.simbongsa.entity.board.Review;
import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.entity.funding.QFunding;
import com.app.simbongsa.search.admin.AdminFundingSearch;
import com.app.simbongsa.type.RequestType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.app.simbongsa.entity.board.QReview.review;
import static com.app.simbongsa.entity.funding.QFunding.funding;

@RequiredArgsConstructor
public class FundingQueryDslImpl implements FundingQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public List<Funding> findAllWithPopular() {
        return query.selectFrom(funding).join(funding.fundingFile).fetchJoin()
                .orderBy(funding.fundingCurrentPrice.divide(funding.fundingTargetPrice).multiply(100).desc())
                .fetch();
    }

    //    펀딩 전체 조회(페이징)
    @Override
    public Page<Funding> findAllWithPaging(AdminFundingSearch adminFundingSearch, Pageable pageable) {
        BooleanExpression fundingTitleLike = adminFundingSearch.getFundingTitle() == null ? null : funding.fundingTitle.like("%" + adminFundingSearch.getFundingTitle() + "%");
        BooleanExpression creatorNameLike = adminFundingSearch.getCreatorNickName() == null ? null : funding.fundingCreator.fundingCreatorNickname.like("%" + adminFundingSearch.getCreatorNickName() + "%");

        List<Funding> foundFunding = query.select(funding)
                .from(funding)
                .where(fundingTitleLike, creatorNameLike)
                .orderBy(funding.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(funding.count())
                .from(funding)
                .fetchOne();

        return new PageImpl<>(foundFunding, pageable, count);
    }

    @Override
    public Optional<Funding> findByIdForDetail(Long fundingId) {

        return Optional.of(
                query.select(funding)
                        .from(funding)
                        .join(funding.fundingFile)
                        .join(funding.fundingGifts)
                        .join(funding.fundingItems)
                        .fetchJoin()
                        .where(funding.id.eq(fundingId))
                        .fetchOne()
        );
    }

    /* 내 펀딩 내역 조회(페이징처리) */
    @Override
    public Page<Funding> findByMemberId_QueryDSL(Pageable pageable, Long memberId) {
        /*memberService.getPays((Long)session.getAttribute("memberId"));*/

        List<Funding> foundFunding = query.select(funding)
                .from(funding)
                .join(funding.fundingFile)
                .fetchJoin()
                .where(funding.member.id.eq(memberId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(funding.count())
                .from(funding)
                .where(funding.member.id.eq(memberId))
                .fetchOne();

        return new PageImpl<>(foundFunding, pageable, count);
    }

//    펀딩 승인, 펀딩 대기 수 구하기
    @Override
    public Long findCountAcceptOrWait(RequestType requestType) {
        return query.select(funding.count())
                .from(funding)
                .where(funding.fundingStatus.eq(requestType))
                .fetchOne();
    }

    //    펀딩 대기를 승인으로 변경
    @Override
    public void updateWaitToAcceptByIds(List<Long> ids) {
        query.update(funding)
                .set(funding.fundingStatus, RequestType.승인)
                .where(funding.id.in(ids))
                .execute();
    }

}
