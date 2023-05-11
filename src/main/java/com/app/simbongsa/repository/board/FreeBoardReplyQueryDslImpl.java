package com.app.simbongsa.repository.board;

import com.app.simbongsa.domain.FreeBoardDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

@RequiredArgsConstructor
public class FreeBoardReplyQueryDslImpl implements FreeBoardReplyQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public Page<FreeBoardDTO> findAllByFreeBoardReplyWithPaging(Long Id, Pageable pageable) {
        return null;
    }

    @Override
    public Long getFreeBoardReplyCount(Long id) {
        return null;
    }
}
