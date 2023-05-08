package com.app.simbongsa.repository.board;

import com.app.simbongsa.domain.search.admin.AdminBoardSearch;
import com.app.simbongsa.entity.board.FreeBoard;

import com.querydsl.core.types.dsl.BooleanExpression;

import com.app.simbongsa.entity.board.QFreeBoard;
import com.app.simbongsa.entity.board.QFreeBoardReply;
import com.app.simbongsa.entity.file.QFreeBoardFile;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static com.app.simbongsa.entity.board.QFreeBoard.freeBoard;
import static com.app.simbongsa.entity.board.QFreeBoardReply.freeBoardReply;
import static com.app.simbongsa.entity.file.QFreeBoardFile.freeBoardFile;
import static com.app.simbongsa.entity.volunteer.QVolunteerWork.volunteerWork;


@RequiredArgsConstructor
public class FreeBoardQueryDslImpl implements FreeBoardQueryDsl {
    private final JPAQueryFactory query;

    //    인기순 목록 조회
    @Override
    public List<FreeBoard> findAllWithPopularFreeBoard() {
        return query.select(freeBoard)
                .from(freeBoard)
                .join(freeBoard.freeBoardReplies, freeBoardReply)
                .fetchJoin()
                .orderBy(freeBoard.freeBoardReplies.size().desc())
                .limit(8)
                .fetch();
    }

    //    자유게시판 전체 조회(페이징)
    @Override
    public Page<FreeBoard> findAllWithPaging(AdminBoardSearch adminBoardSearch, Pageable pageable) {
        BooleanExpression boardTitleLike = adminBoardSearch.getBoardTitle() == null ? null : freeBoard.BoardTitle.like("%" + adminBoardSearch.getBoardTitle() + "%");
        BooleanExpression userEmailLike = adminBoardSearch.getUserEmail() == null ? null : freeBoard.user.userEmail.like("%" + adminBoardSearch.getUserEmail() + "%");

        List<FreeBoard> foundFreeBoard = query.select(freeBoard)
                .from(freeBoard)
                .where(boardTitleLike, userEmailLike)
                .orderBy(freeBoard.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(freeBoard.count())
                .from(freeBoard)
                .fetchOne();

        return new PageImpl<>(foundFreeBoard, pageable, count);
    }


    /* 유저가 작성한 자유게시물 조회(페이징처리) */
    @Override
    public Page<FreeBoard> findByUserId(Pageable pageable, Long userId) {
        List<FreeBoard> foundFreeBoards = query.select(freeBoard)
                .from(freeBoard)
                .where(freeBoard.user.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(freeBoard.count())
                .from(freeBoard)
                .where(freeBoard.user.id.eq(userId))
                .fetchOne();

        return new PageImpl<>(foundFreeBoards,pageable,count);
    }

    //    세션에 담긴 id 값 받아와서 내가 작성한 자유 게시글 리스트 가져오기
    @Override
    public List<FreeBoard> findFreeBoardListByUserId(Pageable pageable, Long userId) {
        return query.select(freeBoard)
                .from(freeBoard)
                .where(freeBoard.user.id.eq(userId))
                .orderBy(freeBoard.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

    }

    //    자유게시판 상세페이지 조회
    @Override
    public Optional<FreeBoard> findByIdForDetail(Long freeBoardId) {
        return Optional.ofNullable(query.select(freeBoard)
                .from(freeBoard)
                .where(freeBoard.id.eq(freeBoardId))
                .fetchOne());
    }
}
