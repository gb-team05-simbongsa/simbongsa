package com.app.simbongsa.repository.volunteer;

import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.search.admin.AdminVolunteerSearch;
import com.app.simbongsa.type.VolunteerWorkCategoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Optional;

public interface VolunteerWorkQueryDsl {
    //    봉사활동 목록 조회
    public List<VolunteerWork> findVolunteerWorkList();
    //    봉사활동 전체 조회(페이징)
    public Page<VolunteerWork> findAllWithPaging(AdminVolunteerSearch adminVolunteerSearch, Pageable pageable);
    //    봉사활동 목록페이지 지역 검색 페이징 처리
    public Page<VolunteerWork> findAllPagingAndSearch(String keyword, Pageable pageable, VolunteerWorkCategoryType volunteerWorkCategoryType);
    //     봉사활동 목록페이지 지역 검색 무한스크롤
    public Optional<VolunteerWork> findById_QueryDSL(Long volunteerWorkId);
    //    봉사활동 카테고리별 목록 조회
    public Page<VolunteerWork> findAllByCategory_QueryDSL(VolunteerWorkCategoryType volunteerWorkCategoryType, Pageable pageable);

//    봉사활동 수정
    public void updateVolunteerWork(VolunteerWork volunteerWork);

//    현재 시퀀스 가져오기
    public VolunteerWork getCurrentSequence_QueryDSL();
}
