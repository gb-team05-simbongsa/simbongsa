//package com.app.simbongsa.repository.inquiry;
//
//import com.app.simbongsa.provider.UserDetail;
//import com.app.simbongsa.search.admin.AdminBoardSearch;
//import com.app.simbongsa.entity.inquiry.Inquiry;
//import com.app.simbongsa.type.InquiryType;
//import com.querydsl.core.types.dsl.BooleanExpression;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//
//import java.util.List;
//
//import static com.app.simbongsa.entity.inquiry.QInquiry.inquiry;
//
//@RequiredArgsConstructor
//public class InquiryQueryDslImpl implements InquiryQueryDsl {
//    private final JPAQueryFactory query;
//
////    문의 전체 조회(페이징 처리)
//    @Override
//    public Page<Inquiry> findAllWithPaging(AdminBoardSearch adminBoardSearch, Pageable pageable) {
//        BooleanExpression inquiryTitleLike = adminBoardSearch.getBoardTitle() == null ? null : inquiry.inquiryTitle.like("%" + adminBoardSearch.getBoardTitle() + "%");
//        BooleanExpression memberEmailLike = adminBoardSearch.getMemberEmail() == null ? null : inquiry.member.memberEmail.like("%" + adminBoardSearch.getMemberEmail() + "%");
//
//        List<Inquiry> foundInquiry = query.select(inquiry)
//                .from(inquiry)
//                .where(inquiryTitleLike, memberEmailLike)
//                .orderBy(inquiry.id.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        Long count = query.select(inquiry.count())
//                .from(inquiry)
//                .where(inquiryTitleLike, memberEmailLike)
//                .fetchOne();
//
//        return new PageImpl<>(foundInquiry, pageable, count);
//    }
//
////    답변 대기중, 답변 완료 개수 조회
//    @Override
//    public Long findInquiryStatusCount(InquiryType inquiryType) {
//        return query.select(inquiry.count())
//                .from(inquiry)
//                .where(inquiry.inquiryStatus.eq(inquiryType))
//                .fetchOne();
//    }
//
////    답변 대기를 답변 완료로 수정
//    @Override
//    public void updateInquiryStatus(Long id) {
//        query.update(inquiry)
//                .set(inquiry.inquiryStatus, InquiryType.답변완료)
//                .where(inquiry.id.eq(id))
//                .execute();
//    }
//
//
//    /* 유저아이디로 문의 페이징처리해서 불러오기 */
//    @Override
//    public Page<Inquiry> findByMemberId(Pageable pageable, Long id) {
//        List<Inquiry> foundInquiries = query.select(inquiry)
//                .from(inquiry)
//                .leftJoin(inquiry.answer)
//                .fetchJoin()
//                .where(inquiry.member.id.eq(id))
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        Long count = query.select(inquiry.count())
//                .from(inquiry)
//                .where(inquiry.member.id.eq(id))
//                .fetchOne();
//
//        return new PageImpl<>(foundInquiries,pageable,count);
//    }
//
//    /* 내 문의사항 수정 */
//    @Override
//    public void updateMyInquiry(String inquiryTitle, String inquiryContent) {
//        query.update(inquiry)
//                .set(inquiry.inquiryTitle, inquiryTitle)
//                .set(inquiry.inquiryContent, inquiryContent)
//                .execute();
//    }
//
//    @Override
//    public Inquiry findInquiryAndAnswerById(Long id) {
//        return query.select(inquiry)
//                .from(inquiry)
//                .leftJoin(inquiry.answer)
//                .fetchJoin()
//                .where(inquiry.id.eq(id))
//                .fetchOne();
//    }
//
//    @Override
//    public void deleteByInquiryId(Long id) {
//        query.delete(inquiry)
//                .where(inquiry.id.eq(id))
//                .execute();
//    }
//}
