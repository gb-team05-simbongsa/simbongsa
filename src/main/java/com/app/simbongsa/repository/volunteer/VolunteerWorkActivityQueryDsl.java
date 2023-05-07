package com.app.simbongsa.repository.volunteer;

import com.app.simbongsa.entity.volunteer.VolunteerWorkActivity;

import java.util.List;
import java.util.Optional;

public interface VolunteerWorkActivityQueryDsl {
//    신청 명단 조회
    public List<VolunteerWorkActivity> findApplyByVolunteerWorkId(Long id);
}
