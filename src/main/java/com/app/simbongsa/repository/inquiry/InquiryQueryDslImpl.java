package com.app.simbongsa.repository.inquiry;

import com.app.simbongsa.entity.inquiry.Inquiry;
import com.app.simbongsa.entity.inquiry.QInquiry;
import com.app.simbongsa.type.InquiryType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.simbongsa.entity.inquiry.QInquiry.inquiry;

@RequiredArgsConstructor
public class InquiryQueryDslImpl implements InquiryQueryDsl {
    private final JPAQueryFactory query;

//    문의 전체 조회(페이징 처리)
    @Override
    public Page<Inquiry> findAllWithPaging(Pageable pageable) {
        List<Inquiry> foundInquiry = query.select(inquiry)
                .from(inquiry)
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
}
