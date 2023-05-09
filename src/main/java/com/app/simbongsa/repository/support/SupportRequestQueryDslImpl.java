package com.app.simbongsa.repository.support;

import com.app.simbongsa.search.admin.AdminSupportRequestSearch;
import com.app.simbongsa.entity.support.SupportRequest;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static com.app.simbongsa.entity.support.QSupport.support;
import static com.app.simbongsa.entity.support.QSupportRequest.supportRequest;

@RequiredArgsConstructor
public class SupportRequestQueryDslImpl implements SupportRequestQueryDsl {
    private final JPAQueryFactory query;

    /* 유저아이디로 후원요청목록 페이징처리해서 불러오기 */
    @Override
    public Page<SupportRequest> findByMemberId(Pageable pageable, Long memberId) {
        List<SupportRequest> foundSupportRequest = query.select(supportRequest)
                .from(supportRequest)
                .where(supportRequest.member.id.eq(memberId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(supportRequest.count())
                .from(supportRequest)
                .where(supportRequest.member.id.eq(memberId))
                .fetchOne();

        return new PageImpl<>(foundSupportRequest,pageable,count);
    }

//  후원 요청 목록페이지 무한스크롤
    @Override
    public Slice<SupportRequest> findAllSupportRequest(Pageable pageable) {
        List<SupportRequest> foundSupportRequest = query.select(supportRequest)
                .from(supportRequest)
                .orderBy(supportRequest.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new SliceImpl<>(foundSupportRequest, pageable, true);
//      hasNext는 현재 페이지에서 다음 페이지가 있는지 여부를 나타내는 불리언(Boolean) 값, true로 설정되면 다음 페이지가 있는 것으로 간주되고,
//      false로 설정되면 다음 페이지가 없는 것으로 간주
    }

//    후원 요청 전체 조회(페이징)
    @Override
    public Page<SupportRequest> findAllWithPaging(AdminSupportRequestSearch adminSupportRequestSearch, Pageable pageable) {
        BooleanExpression requestTypeEq = adminSupportRequestSearch.getRequestType() == null ? null : supportRequest.supportRequestStatus.eq(adminSupportRequestSearch.getRequestType());
        BooleanExpression memberEmailLike = adminSupportRequestSearch.getMemberEmail() == null ? null : supportRequest.member.memberEmail.like("%" + adminSupportRequestSearch.getMemberEmail() + "%");

        List<SupportRequest> foundSupportRequest = query.select(supportRequest)
                .from(supportRequest)
                .where(requestTypeEq, memberEmailLike)
                .orderBy(supportRequest.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(supportRequest.count())
                .from(supportRequest)
                .fetchOne();

        return new PageImpl<>(foundSupportRequest, pageable, count);
    }

    /* 후원 상세페이지, 후원 상세 정보 조회*/
    @Override
    public SupportRequest findByIdWithSupportRequestInfo_QueryDsl(Long id) {
            return query.select(supportRequest).from(supportRequest)
                    .join(supportRequest.supports)
                    .fetchJoin()
                    .where(supportRequest.id.eq(id))
                    .fetchOne();
    }
    @Override
    public Optional<SupportRequest> findSupportRequestDetail_QueryDSL(Long id) {
        return Optional.of(query.select(supportRequest)
                .from(supportRequest)
                .leftJoin(supportRequest.supportRequestFiles)
                .fetchJoin()
                .where(supportRequest.id.eq(id))
                .fetchOne());
    }

    /* 후원 목록 페이지 검색(후원 많은 순, 후원 적은순, 최신순) */
    @Override
    public Slice<SupportRequest> findByIdWithOrder(String keyword, Pageable pageable) {
        List<SupportRequest> foundSupportRequest;
        OrderSpecifier result;
        if(keyword == "후원 많은순"){
            result = supportRequest.supports.any().supportPrice.desc();

        }else if(keyword == "후원 적은순"){

            result = supportRequest.supports.any().supportPrice.asc();

        }else  {
            result = supportRequest.id.desc();
        }
        foundSupportRequest = query.select(supportRequest)
                    .from(supportRequest)
                    .orderBy(result)
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();

        return new SliceImpl<>(foundSupportRequest, pageable, true);

    }

}
