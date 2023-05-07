package com.app.simbongsa.repository.board;

import com.app.simbongsa.domain.ReplyDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

@RequiredArgsConstructor
public class ReviewReplyQueryDslImpl implements ReviewReplyQueryDsl {
    private final JPAQueryFactory query;


    @Override
    public Page<ReplyDTO> findAllByReviewReplyWithPaging(Long Id, Pageable pageable) {
        return null;
    }

    @Override
    public Long getReviewReplyCount(Long id) {
        return null;
    }
}
