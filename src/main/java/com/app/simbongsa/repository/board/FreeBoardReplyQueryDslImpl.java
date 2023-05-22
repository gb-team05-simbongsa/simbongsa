package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.board.FreeBoardReply;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

import static com.app.simbongsa.entity.board.QFreeBoardReply.freeBoardReply;
import static com.app.simbongsa.entity.member.QMember.member;

@RequiredArgsConstructor
public class FreeBoardReplyQueryDslImpl implements FreeBoardReplyQueryDsl {
    private final JPAQueryFactory query;


    @Override
    public Slice<FreeBoardReply> findAllByFreeBoardReplyWithPaging(Long boardId, Pageable pageable){
        List<FreeBoardReply> foundReply = query.select(freeBoardReply)
                .from(freeBoardReply)
                .leftJoin(freeBoardReply.member, member)
                .fetchJoin()
                .where(freeBoardReply.freeBoard.id.eq(boardId))
                .orderBy(freeBoardReply.id.desc())
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
        return query.select(freeBoardReply.count())
                .from(freeBoardReply)
                .where(freeBoardReply.freeBoard.id.eq(boardId))
                .fetchOne();
    }

    @Override
    public void deleteByFreeBoardId(Long boardId){
        query.delete(freeBoardReply)
                .where(freeBoardReply.freeBoard.id.eq(boardId))
                .execute();
    }
}
