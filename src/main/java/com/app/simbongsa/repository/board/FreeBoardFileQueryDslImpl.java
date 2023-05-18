package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.file.FreeBoardFile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FreeBoardFileQueryDslImpl implements FreeBoardFileQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public List<FreeBoardFile> findByFreeBoardId(Long id){return null;}
}
