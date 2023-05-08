package com.app.simbongsa.repository.inquiry;

import com.app.simbongsa.domain.search.admin.AdminInquirySearch;
import com.app.simbongsa.entity.inquiry.Inquiry;
import com.app.simbongsa.entity.inquiry.QInquiry;
import com.app.simbongsa.type.InquiryType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.simbongsa.entity.inquiry.QInquiry.inquiry;
import static com.app.simbongsa.entity.user.QUser.user;

@RequiredArgsConstructor
public class InquiryQueryDslImpl implements InquiryQueryDsl {
    private final JPAQueryFactory query;

//    문의 전체 조회(페이징 처리)
    @Override
    public Page<Inquiry> findAllWithPaging(AdminInquirySearch adminInquirySearch, Pageable pageable) {
        BooleanExpression inquiryTitleLike = adminInquirySearch.getInquiryTitle() == null ? null : inquiry.inquiryTitle.like("%" + adminInquirySearch.getInquiryTitle() + "%");
        BooleanExpression userEmailLike = adminInquirySearch.getUserEmail() == null ? null : inquiry.user.userEmail.like("%" + adminInquirySearch.getUserEmail() + "%");

        List<Inquiry> foundInquiry = query.select(inquiry)
                .from(inquiry)
                .where(inquiryTitleLike, userEmailLike)
                .orderBy(inquiry.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(inquiry.count())
                .from(inquiry)
                .fetchOne();

        return new PageImpl<>(foundInquiry, pageable, count);
    }

//    답변 대기중, 답변 완료 개수 조회
    @Override
    public Long findInquiryStatusCount(InquiryType inquiryType) {
        return query.select(inquiry.count())
                .from(inquiry)
                .where(inquiry.inquiryStatus.eq(inquiryType))
                .fetchOne();
    }

//    답변 대기를 답변 완료로 수정
    @Override
    public void updateInquiryStatus(Long id) {
        query.update(inquiry)
                .set(inquiry.inquiryStatus, InquiryType.답변완료)
                .where(inquiry.id.eq(id))
                .execute();
    }


    /* 유저아이디로 문의 페이징처리해서 불러오기 */
    @Override
    public Page<Inquiry> findByUserId(Pageable pageable, Long userId) {
        List<Inquiry> foundInquiries = query.select(inquiry)
                .from(inquiry)
                .where(inquiry.user.id.eq(userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(inquiry.count())
                .from(inquiry)
                .where(inquiry.user.id.eq(userId))
                .fetchOne();

        return new PageImpl<>(foundInquiries,pageable,count);
    }

}
