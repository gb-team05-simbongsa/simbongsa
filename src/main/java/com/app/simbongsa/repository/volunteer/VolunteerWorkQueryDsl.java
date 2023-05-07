package com.app.simbongsa.repository.volunteer;

import com.app.simbongsa.entity.volunteer.VolunteerWork;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VolunteerWorkQueryDsl {
    //    봉사활동 목록 조회
    public List<VolunteerWork> findVolunteerWorkList();

//    봉사활동 전체 조회(페이징)
    public Page<VolunteerWork> findAllWithPaging(Pageable pageable);
}
