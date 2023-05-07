package com.app.simbongsa.repository.volunteer;

import com.app.simbongsa.entity.volunteer.VolunteerWork;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VolunteerWorkQueryDsl {
    //    봉사활동 목록 조회
    public List<VolunteerWork> findVolunteerWorkList();
    //    봉사활동 목록페이지 페이징
    public Page<VolunteerWork> findAllWithPaging(Pageable pageable);
    //    봉사활동 목록페이지 지역 검색 페이징 처리
    public Page<VolunteerWork> findAllWithPagingAndSearch(String keyword, Pageable pageable);
    //    봉사활동 목록페이지 키워드 검색 페이징 처리
    public Page<VolunteerWork> findAllWithPagingAndMultipleKeywordSearch(String placeKeyword, String agencyKeyword, Pageable pageable);
    //    봉사활동 상세페이지 조회
    public Optional<VolunteerWork> findByIdForDetail(Long volunteerWorkId);

}
