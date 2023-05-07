package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.board.QReview;
import com.app.simbongsa.entity.board.Review;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.simbongsa.entity.board.QReview.review;

@RequiredArgsConstructor
public class ReviewQueryDslImpl implements ReviewQueryDsl {
    private final JPAQueryFactory query;

    /* 유저별 후기게시판 목록 조회 (페이징처리) */
    @Override
    public Page<Review> findByUserId(Pageable pageable, Long userId) {
        List<Review> foundReview = query.select(review)
                .from(review)
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
    public Page<Review> findAllWithPaging(Pageable pageable) {
        List<Review> foundReview = query.select(review)
                .from(review)
                .orderBy(review.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(review.count())
                .from(review)
                .fetchOne();

        return new PageImpl<>(foundReview, pageable, count);
    }
}
