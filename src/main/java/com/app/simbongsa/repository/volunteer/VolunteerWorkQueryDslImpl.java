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
    public Page<VolunteerWork> findAllPagingAndSearch(String keyword, Pageable pageable, VolunteerWorkCategoryType volunteerWorkCategoryType) {
        if(keyword.equals("전체보기") || keyword ==null){
            keyword = "";
        }
        BooleanExpression searchCondition = keyword == null ? null :volunteerWork.volunteerWorkPlace.like(keyword + "%") ;
        BooleanExpression categorySearch = volunteerWorkCategoryType == null ? null : volunteerWork.volunteerWorkCategory.eq(volunteerWorkCategoryType);
        log.info(keyword + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= 키워드" );
        List<VolunteerWork> findAllVolunteer;
        if(volunteerWorkCategoryType == null ){
            findAllVolunteer = query.select(volunteerWork)
                    .from(volunteerWork)
                    .leftJoin(volunteerWork.volunteerWorkFile, volunteerWorkFile)
                    .fetchJoin()
                    .where(searchCondition)
                    .orderBy(volunteerWork.id.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();
        }else{
            findAllVolunteer = query.select(volunteerWork)
                    .from(volunteerWork)
                    .leftJoin(volunteerWork.volunteerWorkFile, volunteerWorkFile)
                    .fetchJoin()
                    .where(searchCondition, categorySearch)
                    .orderBy(volunteerWork.id.desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize())
                    .fetch();
        }


        Long count = query.select(volunteerWork.count())
                .from(volunteerWork)
                .where(searchCondition, categorySearch)
                .fetchOne();

        return new PageImpl<>(findAllVolunteer, pageable, count);
    }



    // 봉사 활동 목록 조회(검색 + 무한스크롤)



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
                .where(volunteerWork.volunteerWorkCategory.eq(volunteerWorkCategoryType))
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

