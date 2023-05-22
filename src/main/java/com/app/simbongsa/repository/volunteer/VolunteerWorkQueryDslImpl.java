package com.app.simbongsa.repository.volunteer;

import com.app.simbongsa.entity.file.QVolunteerWorkFile;
import com.app.simbongsa.entity.volunteer.QVolunteerWork;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.search.admin.AdminVolunteerSearch;
import com.app.simbongsa.type.VolunteerWorkCategoryType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
                .join(volunteerWork.volunteerWorkFile, volunteerWorkFile)
                .fetchJoin()
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
                .where(volunteerWorkPlaceLike, volunteerWorkRegisterAgencyLike)
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

    // 봉사 활동 목록 조회(검색 + 무한스크롤)
    @Override
    public Slice<VolunteerWork> findAllScorollAndSearch(String keyword, Pageable pageable) {
        BooleanBuilder searchCondition = new BooleanBuilder();

        if (keyword != null && !keyword.isEmpty()) {
            if (keyword.equals("서울특별시")) {
                searchCondition.and(volunteerWork.volunteerWorkPlace.eq("서울특별시"));
            } else if (keyword.equals("경기도")) {
                searchCondition.and(volunteerWork.volunteerWorkPlace.eq("경기도"));
            } else if (keyword.equals("충청북도")) {
                searchCondition.and(volunteerWork.volunteerWorkPlace.eq("충청북도"));
            } else if (keyword.equals("충청남도")) {
                searchCondition.and(volunteerWork.volunteerWorkPlace.eq("충청남도"));
            } else if (keyword.equals("경상남도")) {
                searchCondition.and(volunteerWork.volunteerWorkPlace.eq("경상남도"));
            } else if (keyword.equals("경상북도")) {
                searchCondition.and(volunteerWork.volunteerWorkPlace.eq("경상북도"));
            }
        }


        List<VolunteerWork> findAllVolunteer = query.selectFrom(volunteerWork)
                .leftJoin(volunteerWork.volunteerWorkFile, volunteerWorkFile)
                .where(searchCondition)
                .orderBy(volunteerWork.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()).fetch();
        boolean hasNext = findAllVolunteer.size() > pageable.getPageSize();
        if (hasNext) {
            findAllVolunteer.remove(pageable.getPageSize());
        }

        return new SliceImpl<>(findAllVolunteer, pageable, hasNext);
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

    @Override
    public void updateVolunteerWork(VolunteerWork volunteerWorkModify) {
        query.update(volunteerWork)
                .set(volunteerWork.volunteerWorkStartDate, volunteerWorkModify.getVolunteerWorkStartDate())
                .set(volunteerWork.volunteerWorkEndDate, volunteerWorkModify.getVolunteerWorkEndDate())
                .set(volunteerWork.volunteerWorkTime, volunteerWorkModify.getVolunteerWorkTime())
                .set(volunteerWork.volunteerWorkJoinStartDate, volunteerWorkModify.getVolunteerWorkJoinStartDate())
                .set(volunteerWork.volunteerWorkJoinEndDate, volunteerWorkModify.getVolunteerWorkJoinEndDate())
                .set(volunteerWork.volunteerWorkRecruitNumber, volunteerWorkModify.getVolunteerWorkRecruitNumber())
                .set(volunteerWork.volunteerWorkCategory, volunteerWorkModify.getVolunteerWorkCategory())
                .set(volunteerWork.volunteerWorkRegisterAgency, volunteerWorkModify.getVolunteerWorkRegisterAgency())
                .set(volunteerWork.volunteerWorkPlace, volunteerWorkModify.getVolunteerWorkPlace())
                .set(volunteerWork.volunteerWorkTitle, volunteerWorkModify.getVolunteerWorkTitle())
                .set(volunteerWork.volunteerWorkContent, volunteerWorkModify.getVolunteerWorkContent())
                .where(volunteerWork.id.eq(volunteerWorkModify.getId()))
                .execute();
    }

    @Override
    public VolunteerWork getCurrentSequence_QueryDSL() {
        return query.select(volunteerWork)
                .from(volunteerWork)
                .orderBy(volunteerWork.id.desc())
                .limit(1)
                .fetchOne();
    }

}

