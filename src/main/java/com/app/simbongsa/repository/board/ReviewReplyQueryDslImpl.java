package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.board.ReviewReply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static com.app.simbongsa.entity.board.QReviewReply.reviewReply;
import static com.app.simbongsa.entity.member.QMember.member;

@RequiredArgsConstructor
public class ReviewReplyQueryDslImpl implements ReviewReplyQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Slice<ReviewReply> findAllByReviewReplyWithPaging(Long reviewId, Pageable pageable){
        List<ReviewReply> foundReply = query.select(reviewReply)
                .from(reviewReply)
                .leftJoin(reviewReply.member, member)
                .fetchJoin()
                .where(reviewReply.review.id.eq(reviewId))
                .orderBy(reviewReply.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        boolean hasNext = false;
        if (foundReply.size() > pageable.getPageSize()) {
            foundReply.remove(pageable.getPageSize());
            hasNext = true;
        }

        return new SliceImpl<>(foundReply, pageable, hasNext);
    }

    @Override
    public Long getReplyCount_QueryDsl(Long reviewId){
        return query.select(reviewReply.count())
                .from(reviewReply)
                .where(reviewReply.review.id.eq(reviewId))
                .fetchOne();
    }

    @Override
    public void deleteByReviewId(Long reviewId){
        query.delete(reviewReply)
                .where(reviewReply.review.id.eq(reviewId))
                .execute();
    }

}
