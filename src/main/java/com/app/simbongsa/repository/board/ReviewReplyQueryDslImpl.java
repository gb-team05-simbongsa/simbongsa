package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.board.ReviewReply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static com.app.simbongsa.entity.board.QReviewReply.reviewReply;

@Slf4j
@RequiredArgsConstructor
public class ReviewReplyQueryDslImpl implements ReviewReplyQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Slice<ReviewReply> findAllByReviewReplyWithPaging(Long boardId, Pageable pageable){
        List<ReviewReply> foundReply = query.select(reviewReply)
                .from(reviewReply)
                .where(reviewReply.review.id.eq(boardId))
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
    public Long getReplyCount_QueryDsl(Long boardId){
        return query.select(reviewReply.count())
                .from(reviewReply)
                .where(reviewReply.review.id.eq(boardId))
                .fetchOne();
    }

    @Override
    public void deleteByReviewId(Long boardId){
        query.delete(reviewReply)
                .where(reviewReply.review.id.eq(boardId))
                .execute();
    }

}
