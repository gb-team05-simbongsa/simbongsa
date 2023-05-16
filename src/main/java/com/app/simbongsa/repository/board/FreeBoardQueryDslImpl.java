package com.app.simbongsa.repository.board;

import com.app.simbongsa.entity.board.QFreeBoard;
import com.app.simbongsa.entity.file.FreeBoardFile;
import com.app.simbongsa.entity.inquiry.Inquiry;
import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.search.admin.AdminBoardSearch;
import com.app.simbongsa.entity.board.FreeBoard;

import com.querydsl.core.types.dsl.BooleanExpression;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;
import java.util.Optional;

import static com.app.simbongsa.entity.board.QFreeBoard.freeBoard;
import static com.app.simbongsa.entity.board.QFreeBoardReply.freeBoardReply;
import static com.app.simbongsa.entity.inquiry.QInquiry.inquiry;


@RequiredArgsConstructor
public class FreeBoardQueryDslImpl implements FreeBoardQueryDsl {
    private final JPAQueryFactory query;

    //    최신순 목록 조회(무한스크롤)
    @Override
    public Slice<FreeBoard> findAllByIdDescWithPaging_QueryDSL(Pageable pageable) {
        List<FreeBoard> freeBoards = query.select(freeBoard)
                .from(freeBoard)
                .join(freeBoard.member)
                .fetchJoin()
                .join(freeBoard.freeBoardFiles)
                .fetchJoin()
                .orderBy(freeBoard.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return checkLastPage(pageable, freeBoards);
    }

    //    인기순 목록 조회(무한스크롤)
    @Override
    public Slice<FreeBoard> findAllByLikeCountDescWithPaging_QueryDSL(Pageable pageable) {
        List<FreeBoard> freeBoards = query.select(freeBoard)
                .from(freeBoard)
                .join(freeBoard.member)
                .fetchJoin()
                .join(freeBoard.freeBoardFiles)
                .fetchJoin()
                .orderBy(freeBoard.freeBoardReplies.size().desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return checkLastPage(pageable, freeBoards);
    }

    @Override
    public FreeBoard getCurrentSequence_QueryDsl() {
        return query.select(freeBoard)
                .from(freeBoard)
                .orderBy(freeBoard.id.desc())
                .limit(1)
                .fetchOne();
    }

    //    메인페이지 인기순 목록 조회
    @Override
    public List<FreeBoard> findAllWithPopularFreeBoard() {
        return query.select(freeBoard)
                .from(freeBoard)
                .join(freeBoard.freeBoardReplies, freeBoardReply)
                .fetchJoin()
                .join(freeBoard.freeBoardFiles)
                .fetchJoin()
                .orderBy(freeBoard.freeBoardReplyCount.desc())
                .limit(10)
                .fetch();
    }


    //    자유게시판 전체 조회(페이징)
    @Override
    public Page<FreeBoard> findAllWithPaging(AdminBoardSearch adminBoardSearch, Pageable pageable) {
        BooleanExpression boardTitleLike = adminBoardSearch.getBoardTitle() == null ? null : freeBoard.boardTitle.like("%" + adminBoardSearch.getBoardTitle() + "%");
        BooleanExpression memberEmailLike = adminBoardSearch.getMemberEmail() == null ? null : freeBoard.member.memberEmail.like("%" + adminBoardSearch.getMemberEmail() + "%");

        List<FreeBoard> foundFreeBoard = query.select(freeBoard)
                .from(freeBoard)
                .where(boardTitleLike, memberEmailLike)
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
    public Page<FreeBoard> findByMemberId(Pageable pageable, @AuthenticationPrincipal UserDetail userDetail) {
        Long memberId = userDetail.getId();
        List<FreeBoard> foundFreeBoards = query.select(freeBoard)
                .from(freeBoard)
                .leftJoin(freeBoard.freeBoardFiles)
                .fetchJoin()
                .where(freeBoard.member.id.eq(memberId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(freeBoard.count())
                .from(freeBoard)
                .where(freeBoard.member.id.eq(memberId))
                .fetchOne();

        return new PageImpl<>(foundFreeBoards,pageable,count);
    }

    //    세션에 담긴 id 값 받아와서 내가 작성한 자유 게시글 리스트 가져오기
    @Override
    public List<FreeBoard> findFreeBoardListByMemberId(Pageable pageable, Long memberId) {
        return query.select(freeBoard)
                .from(freeBoard)
                .where(freeBoard.member.id.eq(memberId))
                .orderBy(freeBoard.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

    }

    //    자유게시판 상세페이지 조회
    @Override
    public Optional<FreeBoard> findByIdForDetail_QueryDsl(Long freeBoardId) {
        return Optional.ofNullable(query.select(freeBoard)
                .from(freeBoard)
                .join(freeBoard.freeBoardFiles)
                .fetchJoin()
                .where(freeBoard.id.eq(freeBoardId))
                .fetchOne());
    }

    /* 마이페이지 작성한 자유게시물 상세 조회*/
    @Override
    public Optional<FreeBoard> findByIdForMyDetail(Long freeBoardId) {
        return Optional.ofNullable(query.select(freeBoard)
                .from(freeBoard)
                .join(freeBoard.freeBoardFiles)
                .fetchJoin()
                .where(freeBoard.id.eq(freeBoardId))
                .fetchOne());
    }

    //    hasNext true인지 false인지 체크하는 메소드(마지막 페이지 체크)
    private Slice<FreeBoard> checkLastPage(Pageable pageable, List<FreeBoard> freeBoards) {

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 크면 뒤에 더 있음, next = true
        if (freeBoards.size() > pageable.getPageSize()) {
            hasNext = true;
            freeBoards.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(freeBoards, pageable, hasNext);
    }

    /* 내 자유게시물 수정 */

}
