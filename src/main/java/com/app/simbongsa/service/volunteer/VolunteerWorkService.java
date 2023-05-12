package com.app.simbongsa.service.volunteer;

import com.app.simbongsa.domain.InquiryDTO;
import com.app.simbongsa.domain.VolunteerWorkDTO;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.search.admin.AdminBoardSearch;
import com.app.simbongsa.search.admin.AdminVolunteerSearch;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VolunteerWorkService {
    // 1. 메인페이지 - 봉사 활동 8개 TEST
    public List<VolunteerWorkDTO> getVolunteerList();

//    봉사 전체 조회
    public Page<VolunteerWorkDTO> getVolunteerWork(Integer page, AdminVolunteerSearch adminVolunteerSearch);

//    봉사 상세보기
    public VolunteerWorkDTO getVolunteerWorkDetail(Long id);

//    봉사 삭제
    public void deleteVolunteerWorkByIds(List<Long> ids);

    default VolunteerWorkDTO toVolunteerWorkDTO(VolunteerWork volunteerWork) {
        return VolunteerWorkDTO.builder()
                .volunteerWorkCategory(volunteerWork.getVolunteerWorkCategory())
                .volunteerWorkPlace(volunteerWork.getVolunteerWorkPlace())
                .volunteerWorkTitle(volunteerWork.getVolunteerWorkTitle())
                .build();
    }
}
