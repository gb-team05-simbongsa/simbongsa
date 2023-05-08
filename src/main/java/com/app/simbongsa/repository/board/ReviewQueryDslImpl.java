package com.app.simbongsa.repository.board;

import com.app.simbongsa.domain.search.admin.AdminBoardSearch;
import com.app.simbongsa.entity.board.Review;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.app.simbongsa.entity.board.QReview.review;


import static com.app.simbongsa.entity.board.QReviewReply.reviewReply;


@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {
    private final JPAQueryFactory query;

    /* 내 후기 게시물 목록 조회 (페이징처리) */
    @Override
    public Page<Review> findByUserId(Pageable pageable, Long userId) {
        List<Review> foundReview = query.select(review)
                .from(review)
                .join(review.reviewFiles)
                .fetchJoin()
                .where(review.user.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        Long count = query.select(review.count())
                .from(review)
                .where(review.user.id.eq(userId))
                .fetchOne();

        return new PageImpl<>(foundReview, pageable, count);
    }

    //    후기 목록 전체 조회(페이징)
    @Override
    public Page<Review> findAllWithPaging(AdminBoardSearch adminBoardSearch, Pageable pageable) {
        BooleanExpression boardTitleLike = adminBoardSearch.getBoardTitle() == null ? null : review.BoardTitle.like("%" + adminBoardSearch.getBoardTitle() + "%");
        BooleanExpression userEmailLike = adminBoardSearch.getUserEmail() == null ? null : review.user.userEmail.like("%" + adminBoardSearch.getUserEmail() + "%");

        List<Review> foundReview = query.select(review)
                .from(review)
                .where(boardTitleLike, userEmailLike)
                .orderBy(review.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(review.count())
                .from(review)
                .fetchOne();

        return new PageImpl<>(foundReview, pageable, count);
    }

    //    세션에 담긴 id 값 받아와서 내가 작성한 자유 게시글 리스트 가져오기
    @Override
    public List<Review> findReviewListByUserId(Pageable pageable, Long userId) {
        return query.select(review)
                .from(review)
                .where(review.user.id.eq(userId))
                .orderBy(review.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    //    인기순 목록 조회
    @Override
    public List<Review> findAllWithPopularReview() {
        return query.selectFrom(review).limit(8).orderBy(reviewReply.id.desc()).fetch();
    }

    //    후기 게시판 상세페이지 조회
    @Override
    public Optional<Review> findByIdForDetail(Long reviewId) {
        return Optional.ofNullable(query.select(review)
                .from(review)
                .where(review.id.eq(reviewId))
                .fetchOne());
    }
}
