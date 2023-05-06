package com.app.simbongsa.repository.support;

import com.app.simbongsa.entity.support.QSupportRequest;
import com.app.simbongsa.entity.support.SupportRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.app.simbongsa.entity.support.QSupportRequest.supportRequest;

@RequiredArgsConstructor
public class SupportRequestQueryDslImpl implements SupportRequestQueryDsl {
    private final JPAQueryFactory query;

    /* 유저아이디로 후원요청목록 페이징처리해서 불러오기 */
    @Override
    public Page<SupportRequest> findByUserId(Pageable pageable, Long id) {
        List<SupportRequest> foundSupportRequest = query.select(supportRequest)
                .from(supportRequest)
                .where(supportRequest.user.id.eq(id))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(supportRequest.count())
                .from(supportRequest)
                .where(supportRequest.user.id.eq(id))
                .fetchOne();

        return new PageImpl<>(foundSupportRequest,pageable,count);
    }
}
