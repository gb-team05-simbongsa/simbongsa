package com.app.simbongsa.service.volunteer;

import com.app.simbongsa.domain.VolunteerWorkDTO;
import com.app.simbongsa.entity.volunteer.VolunteerWork;

import java.util.List;

public interface VolunteerWorkService {
    public List<VolunteerWorkDTO> getVolunteerList();
    // 1. 메인페이지 - 봉사 활동 8개 TEST
    default VolunteerWorkDTO toVolunteerWorkDTO(VolunteerWork volunteerWork) {
        return VolunteerWorkDTO.builder()
                .volunteerWorkCategory(volunteerWork.getVolunteerWorkCategory())
                .volunteerWorkPlace(volunteerWork.getVolunteerWorkPlace())
                .volunteerWorkTitle(volunteerWork.getVolunteerWorkTitle())
                .build();
    }
}
