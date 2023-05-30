package com.app.simbongsa.repository.board;

import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.entity.board.FreeBoard;
import com.app.simbongsa.entity.file.QReviewFile;
import com.app.simbongsa.entity.file.ReviewFile;
import com.app.simbongsa.entity.member.QMember;
import com.app.simbongsa.search.admin.AdminBoardSearch;
import com.app.simbongsa.entity.board.Review;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static com.app.simbongsa.entity.board.QReview.review;


import static com.app.simbongsa.entity.board.QReviewReply.reviewReply;
import static com.app.simbongsa.entity.file.QReviewFile.reviewFile;
import static com.app.simbongsa.entity.member.QMember.member;


@RequiredArgsConstructor
@Slf4j
public class ReviewQueryDslImpl implements ReviewQueryDsl {
    private final JPAQueryFactory query;

     //   최신순 목록 조회(무한스크롤)
    @Override
    public Slice<Review> findAllByIdReviewPaging_QueryDSL(Pageable pageable) {
        List<Review> reviews = query.select(review)
                .from(review)
                .leftJoin(review.member, member)
                .fetchJoin()
                .leftJoin(review.reviewFiles, reviewFile)
                .fetchJoin()
                .orderBy(review.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = false;
        if (reviews.size() > pageable.getPageSize()){
            reviews.remove(pageable.getPageSize());

            hasNext = true;
        }
        log.info(hasNext + "============");
        return new SliceImpl<>(reviews, pageable, hasNext);
    }

    //    인기순 목록 조회(무한스크롤)
    @Override
    public Slice<Review> findAllByLikeCountReviewPaging_QueryDSL(Pageable pageable) {
        List<Review> reviews = query.select(review)
                .from(review)
                .leftJoin(review.member, member)
                .fetchJoin()
                .leftJoin(review.reviewFiles, reviewFile)
                .fetchJoin()
                .orderBy(review.reviewReplies.size().desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = false;
        if (reviews.size() > pageable.getPageSize()){
            reviews.remove(pageable.getPageSize());

            hasNext = true;
        }
        log.info(hasNext + "============");
        return new SliceImpl<>(reviews, pageable, hasNext);
    }

    // 시퀀스
    @Override
    public Review getCurrentSequence_QueryDsl() {
        return query.select(review)
                .from(review)
                .orderBy(review.id.desc())
                .limit(1)
                .fetchOne();
    }

    /* 내 후기 게시물 목록 조회 (페이징처리) */
    @Override
    public Page<Review> findByMemberId(Pageable pageable, MemberDTO memberDTO) {
        Long memberId = memberDTO.getId();
        List<Review> foundReview = query.select(review)
                .from(review)
                .leftJoin(review.reviewFiles)
                .fetchJoin()
                .where(review.member.id.eq(memberId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        Long count = query.select(review.count())
                .from(review)
                .where(review.member.id.eq(memberId))
                .fetchOne();

        return new PageImpl<>(foundReview, pageable, count);
    }

    //    후기게시판 전체 조회(페이징)
    @Override
    public Page<Review> findAllWithPaging(AdminBoardSearch adminBoardSearch, Pageable pageable) {
        BooleanExpression boardTitleLike = adminBoardSearch.getBoardTitle() == null ? null : review.boardTitle.like("%" + adminBoardSearch.getBoardTitle() + "%");
        BooleanExpression memberEmailLike = adminBoardSearch.getMemberEmail() == null ? null : review.member.memberEmail.like("%" + adminBoardSearch.getMemberEmail() + "%");

        List<Review> foundReview = query.select(review)
                .from(review)
                .where(boardTitleLike, memberEmailLike)
                .orderBy(review.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(review.count())
                .from(review)
                .where(boardTitleLike, memberEmailLike)
                .fetchOne();

        return new PageImpl<>(foundReview, pageable, count);
    }

    //    세션에 담긴 id 값 받아와서 내가 작성한 자유 게시글 리스트 가져오기
    @Override
    public List<Review> findReviewListByMemberId(Pageable pageable, Long memberId) {
        return query.select(review)
                .from(review)
                .where(review.member.id.eq(memberId))
                .orderBy(review.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    //    메인 인기순 목록 조회
    @Override
    public List<Review> findAllWithPopularReview() {
        return query.selectFrom(review).limit(8).orderBy(reviewReply.id.desc()).fetch();
    }

    //    후기게시판 상세페이지 조회
    @Override
    public Optional<Review> findByIdForDetail_QueryDsl(Long reviewId) {
        return Optional.ofNullable(query.select(review)
                .from(review)
                .join(review.reviewFiles)
                .fetchJoin()
                .where(review.id.eq(reviewId))
                .fetchOne());
    }

    /* 마이페이지 작성한 후기게시물 상세 조회*/
    @Override
    public Optional<Review> findByIdForMyDetail(Long reviewId) {
        return Optional.ofNullable(query.select(review)
                .from(review)
                .join(review.reviewFiles)
                .fetchJoin()
                .where(review.id.eq(reviewId))
                .fetchOne());
    }

    //    hasNext true인지 false인지 체크하는 메소드(마지막 페이지 체크)
    private Slice<Review> checkLastPage(Pageable pageable, List<Review> reviews) {

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (reviews.size() > pageable.getPageSize()) {
            hasNext = true;
            reviews.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(reviews, pageable, hasNext);
    }

    /*마이페이지 내가 작성한 글 전체 조회*/
    @Override
    public Page<Review> findAllByReviewMemberIdPaging_QueryDsl(Pageable pageable, Long id){
        List<Review> foundReview = query.select(review)
                .from(review)
                .where(review.member.id.eq(id))
                .leftJoin(review.reviewFiles)
                .fetchJoin()
                .orderBy(review.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(review.count())
                .from(review)
                .where(review.member.id.eq(id))
                .fetchOne();
        return new PageImpl<>(foundReview, pageable, count);
    }
}
