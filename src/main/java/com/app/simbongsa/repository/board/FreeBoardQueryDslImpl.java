package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.board.FreeBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.simbongsa.entity.board.QFreeBoard.freeBoard;
import static com.app.simbongsa.entity.board.QFreeBoardReply.freeBoardReply;

@RequiredArgsConstructor
public class FreeBoardQueryDslImpl implements FreeBoardQueryDsl {
    private final JPAQueryFactory query;

    @Override
    public List<FreeBoard> findAllWithPopularFreeBoard() {
        return query.selectFrom(freeBoard).limit(8).orderBy(freeBoardReply.id.desc()).fetch();
    }

//    자유게시판 전체 조회(페이징)
    @Override
    public Page<FreeBoard> findAllWithPaging(Pageable pageable) {
        List<FreeBoard> foundFreeBoard = query.select(freeBoard)
                .from(freeBoard)
                .orderBy(freeBoard.id.desc())
                .join(freeBoard.user)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(freeBoard.count())
                .from(freeBoard)
                .fetchOne();

        return new PageImpl<>(foundFreeBoard, pageable, count);
    }
}
