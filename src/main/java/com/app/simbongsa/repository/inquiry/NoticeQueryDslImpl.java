package com.app.simbongsa.repository.inquiry;

import com.app.simbongsa.search.admin.AdminNoticeSearch;
import com.app.simbongsa.entity.inquiry.Notice;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.simbongsa.entity.inquiry.QNotice.notice;

@RequiredArgsConstructor
public class NoticeQueryDslImpl implements NoticeQueryDsl {
    private final JPAQueryFactory query;

//    공지사항 전체조회(페이징)
    @Override
    public Page<Notice> findAllWithPaging(AdminNoticeSearch adminNoticeSearch, Pageable pageable) {
        BooleanExpression noticeTitleLike = adminNoticeSearch.getNoticeTitle() == null ? null : notice.noticeTitle.like("%" + adminNoticeSearch.getNoticeTitle() + "%");
        BooleanExpression noticeContentLike = adminNoticeSearch.getNoticeContent() == null ? null : notice.noticeContent.like("%" + adminNoticeSearch.getNoticeContent() + "%");

        List<Notice> foundNotice = query.select(notice)
                .from(notice)
                .where(noticeTitleLike, noticeContentLike)
                .orderBy(notice.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(notice.count())
                .from(notice)
                .fetchOne();

        return new PageImpl<>(foundNotice, pageable, count);
    }
}
