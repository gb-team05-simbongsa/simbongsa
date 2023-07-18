package com.app.simbongsa.repository.support;

import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.domain.SupportRequestDTO;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.entity.support.QSupport;
import com.app.simbongsa.entity.support.QSupportRequest;
import com.app.simbongsa.entity.support.Support;
import com.app.simbongsa.search.admin.AdminSupportRequestSearch;
import com.app.simbongsa.entity.support.SupportRequest;
import com.app.simbongsa.type.RequestType;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.app.simbongsa.entity.file.QSupportRequestFile.supportRequestFile;
import static com.app.simbongsa.entity.member.QMember.member;
import static com.app.simbongsa.entity.support.QSupport.support;
import static com.app.simbongsa.entity.support.QSupportRequest.supportRequest;

@RequiredArgsConstructor
public class SupportRequestQueryDslImpl implements SupportRequestQueryDsl {
    private final JPAQueryFactory query;

    /* 유저아이디로 후원요청목록 페이징처리해서 불러오기 */
    @Override
    public Page<SupportRequest> findByMemberId(Pageable pageable, MemberDTO memberDTO) {
        List<SupportRequest> foundSupportRequest = query.select(supportRequest)
                .from(supportRequest)
                .where(supportRequest.member.id.eq(memberDTO.getId()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(supportRequest.count())
                .from(supportRequest)
                .where(supportRequest.member.id.eq(memberDTO.getId()))
                .fetchOne();

        return new PageImpl<>(foundSupportRequest,pageable,count);
    }

//    후원 요청 전체 조회(페이징)
    @Override
    public Page<SupportRequest> findAllWithPaging(AdminSupportRequestSearch adminSupportRequestSearch, Pageable pageable) {
        BooleanExpression requestTypeEq = adminSupportRequestSearch.getRequestType() == null ? null : supportRequest.supportRequestStatus.eq(adminSupportRequestSearch.getRequestType());
        BooleanExpression memberEmailLike = adminSupportRequestSearch.getMemberEmail() == null ? null : supportRequest.member.memberEmail.like("%" + adminSupportRequestSearch.getMemberEmail() + "%");

        List<SupportRequest> foundSupportRequest = query.select(supportRequest)
                .from(supportRequest)
                .where(requestTypeEq, memberEmailLike)
                .orderBy(supportRequest.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(supportRequest.count())
                .from(supportRequest)
                .where(requestTypeEq, memberEmailLike)
                .fetchOne();

        return new PageImpl<>(foundSupportRequest, pageable, count);
    }



    /* 후원 상세페이지, 후원 상세 정보 조회*/
    @Override
    public Optional<SupportRequest> findSupportRequestDetail_QueryDSL(Long id) {
        return Optional.of(query.select(supportRequest)
                .from(supportRequest)
                .leftJoin(supportRequest.supportRequestFiles)
                .fetchJoin()
                .where(supportRequest.id.eq(id))
                .fetchOne());
    }

    //    후원 요청 전체조회 - 페이징(후원 목록 페이지)
    @Override
    public Page<SupportRequest> findAllWithPagingSearch_QueryDSL(String keyword , Pageable pageable) {
        OrderSpecifier result;

        if(keyword.equals("참여 많은순")){
            result = supportRequest.supports.size().desc();

        }else if(keyword.equals("참여 적은순")){

            result = supportRequest.supports.size().asc();
        }else  {
            result = supportRequest.id.desc();
        }

        List<SupportRequest> foundSupportRequest = query.select(supportRequest)
                .from(supportRequest)
                .join(supportRequest.supports, support)
                .fetchJoin()
                .where(supportRequest.supportRequestStatus.eq(RequestType.승인))
                .orderBy(result)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        Long count = query.select(supportRequest.count())
                .from(supportRequest)
                .where(supportRequest.supportRequestStatus.eq(RequestType.승인))
                .fetchOne();


        return new PageImpl<>(foundSupportRequest, pageable, count);
    }

    @Override
    public SupportRequest getCurrentSequence_QueryDsl() {
        return query.select(supportRequest)
                .from(supportRequest)
                .orderBy(supportRequest.id.desc())
                .limit(1)
                .fetchOne();
    }

    //    대기를 승인으로 변경
    @Override
    public void updateWaitToAccessByIds(List<Long> ids, RequestType requestType) {
        query.update(supportRequest)
                .set(supportRequest.supportRequestStatus, requestType)
                .where(supportRequest.id.in(ids))
                .execute();
    }

    @Override
    public Long countStatusWaitAccessDenied(RequestType requestType) {
        return query.select(supportRequest.count())
                .from(supportRequest)
                .where(supportRequest.supportRequestStatus.eq(requestType))
                .fetchOne();
    }
    // 공양미 후원




}
