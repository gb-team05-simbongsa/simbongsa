package com.app.simbongsa.repository.volunteer;

import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.search.admin.AdminVolunteerSearch;
import com.app.simbongsa.type.VolunteerWorkCategoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VolunteerWorkQueryDsl {
    //    봉사활동 목록 조회
    public List<VolunteerWork> findVolunteerWorkList();
    //    봉사활동 전체 조회(페이징)
    public Page<VolunteerWork> findAllWithPaging(AdminVolunteerSearch adminVolunteerSearch, Pageable pageable);
    //    봉사활동 목록페이지 지역 검색 페이징 처리
    public Page<VolunteerWork> findAllPagingAndSearch(String keyword, Pageable pageable);
    //    봉사활동 목록페이지 키워드 검색 페이징 처리
    public Page<VolunteerWork> findPagingAndSearch(String placeKeyword, String agencyKeyword, Pageable pageable);
    //    봉사활동 상세페이지 조회
    public Optional<VolunteerWork> findById_QueryDSL(Long volunteerWorkId);
    //    봉사활동 이전글 다음글 조회
    public Optional<VolunteerWork> findByNextIdOrPrevId(String keyword, Long id);
    //    봉사활동 카테고리별 목록 조회
    public Page<VolunteerWork> findAllByCategory_QueryDSL(VolunteerWorkCategoryType volunteerWorkCategoryType, Pageable pageable);

//    현재 시퀀스 가져오기
    public VolunteerWork getCurrentSequence_QueryDSL();
}
