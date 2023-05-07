package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.board.ReviewReply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReviewReplyQueryDslImpl implements ReviewReplyQueryDsl {
    private final JPAQueryFactory query;
    private ReviewReply riverwReply;

//    @Override
//    public Long getReviewReplyCount(Long id) {
//        return query.select(riverwReply.count())
//                .from(riverwReply)
//                .where(riverwReply.id.eq(id))
//                .fetchOne();
//    }
}
