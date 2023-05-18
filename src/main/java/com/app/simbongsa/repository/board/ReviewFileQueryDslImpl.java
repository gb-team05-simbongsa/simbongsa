package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.file.ReviewFile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ReviewFileQueryDslImpl implements ReviewFileQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public List<ReviewFile> findByReviewId(Long id){return null;}
}
