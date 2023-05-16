package com.app.simbongsa.repository.volunteer;

import com.app.simbongsa.entity.file.QVolunteerWorkFile;
import com.app.simbongsa.entity.volunteer.QVolunteerWork;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.search.admin.AdminVolunteerSearch;
import com.app.simbongsa.type.VolunteerWorkCategoryType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.app.simbongsa.entity.file.QVolunteerWorkFile.volunteerWorkFile;
import static com.app.simbongsa.entity.volunteer.QVolunteerWork.volunteerWork;

@RequiredArgsConstructor
@Slf4j
public class VolunteerWorkQueryDslImpl implements VolunteerWorkQueryDsl {
    private final JPAQueryFactory query;

    // 봉사활동 목록 조회 (메인페이지)
    @Override
    public List<VolunteerWork> findVolunteerWorkList() {
        return query.select(volunteerWork)
                .from(volunteerWork)
                .leftJoin(volunteerWork.volunteerWorkFiles, volunteerWorkFile)
                .orderBy(volunteerWork.id.desc())
                .limit(8)
                .fetch();
    }


//    봉사활동 전체 조회(페이지)
    @Override
    public Page<VolunteerWork> findAllWithPaging(AdminVolunteerSearch adminVolunteerSearch, Pageable pageable) {
        BooleanExpression volunteerWorkPlaceLike = adminVolunteerSearch.getVolunteerWorkPlace() == null ? null : volunteerWork.volunteerWorkPlace.like("%" + adminVolunteerSearch.getVolunteerWorkPlace() + "%");
        BooleanExpression volunteerWorkRegisterAgencyLike = adminVolunteerSearch.getVolunteerWorkRegisterAgency() == null ? null : volunteerWork.volunteerWorkRegisterAgency.like("%" + adminVolunteerSearch.getVolunteerWorkRegisterAgency() + "%");

        List<VolunteerWork> foundVolunteerWork = query.select(volunteerWork)
                .from(volunteerWork)
                .where(volunteerWorkPlaceLike, volunteerWorkRegisterAgencyLike)
                .orderBy(volunteerWork.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(volunteerWork.count())
                .from(volunteerWork)
                .fetchOne();

        return new PageImpl<>(foundVolunteerWork, pageable, count);
    }


    // 봉사 활동 목록 조회 (검색 + 페이징)
    @Override
    public Page<VolunteerWork> findAllPagingAndSearch(String keyword, Pageable pageable) {
        BooleanExpression searchCondition = volunteerWork.volunteerWorkPlace.containsIgnoreCase(keyword);
        List<VolunteerWork> findAllVolunteer = query.select(volunteerWork)
                .from(volunteerWork)
                .where(searchCondition)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long count = query.select(volunteerWork.count())
                .from(volunteerWork)
                .fetchOne();

        return new PageImpl<>(findAllVolunteer, pageable, count);
    }

    @Override
    public Page<VolunteerWork> findPagingAndSearch(String placeKeyword, String agencyKeyword, Pageable pageable) {
        List<BooleanExpression> searchConditions = new ArrayList<>();

        if (placeKeyword != null && !placeKeyword.isEmpty()) {
            searchConditions.add(volunteerWork.volunteerWorkPlace.containsIgnoreCase(placeKeyword));
        }
        if (agencyKeyword != null && !agencyKeyword.isEmpty()) {
            searchConditions.add(volunteerWork.volunteerWorkRegisterAgency.containsIgnoreCase(agencyKeyword));
        }

        BooleanExpression finalCondition = searchConditions.stream().reduce(BooleanExpression::or).orElse(null);
        List<VolunteerWork> findAllVolunteerWorkKeywords = query.select(volunteerWork)
                .from(volunteerWork)
                .where(finalCondition)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(volunteerWork.count())
                .from(volunteerWork)
                .where(finalCondition)
                .fetchOne();

        return new PageImpl<>(findAllVolunteerWorkKeywords, pageable, count);
    }
    //    봉사활동 상세페이지 조회
    @Override
    public Optional<VolunteerWork> findById_QueryDSL(Long volunteerWorkId) {
        return Optional.ofNullable(query.selectFrom(volunteerWork)
                .where(volunteerWork.id.eq(volunteerWorkId))
                .fetchOne());
     }
    //    이전글 다음글
    @Override
    public Optional<VolunteerWork> findByNextIdOrPrevId(String keyword, Long id) {
        BooleanExpression prevPage = volunteerWork.id.lt(id);
        BooleanExpression nextPage = volunteerWork.id.gt(id);

        if(keyword == "다음글"){
            return Optional.ofNullable(query.selectFrom(volunteerWork)
                    .where(nextPage)
                    .fetchFirst());
        }else if(keyword == "이전글"){
            return Optional.ofNullable(query.selectFrom(volunteerWork)
                    .where(prevPage)
                    .fetchFirst());
        }else {
            return Optional.ofNullable(query.selectFrom(volunteerWork)
                    .where(volunteerWork.id.eq(id))
                    .fetchOne());
        }


    }
    // 봉사활동 카테고리별 목록조회
    @Override
    public Page<VolunteerWork> findAllByCategory_QueryDSL(VolunteerWorkCategoryType volunteerWorkCategoryType, Pageable pageable) {

        List<VolunteerWork> foundVolunteerWork = query.select(volunteerWork)
                .from(volunteerWork)
                .where(volunteerWork.volunteerWorkCategory.eq(volunteerWorkCategoryType))
                .orderBy(volunteerWork.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = query.select(volunteerWork.count())
                .from(volunteerWork)
                .fetchOne();

        return new PageImpl<>(foundVolunteerWork, pageable, count);
    }

}

