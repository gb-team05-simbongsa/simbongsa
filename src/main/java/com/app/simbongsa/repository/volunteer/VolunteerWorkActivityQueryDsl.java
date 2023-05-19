package com.app.simbongsa.repository.volunteer;

import com.app.simbongsa.entity.volunteer.VolunteerWorkActivity;
import com.app.simbongsa.provider.UserDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;
import java.util.Optional;

public interface VolunteerWorkActivityQueryDsl {
//    신청 명단 조회
    public List<VolunteerWorkActivity> findApplyByVolunteerWorkId(Long id);

    /* 내 봉사 활동 목록 조회(페이징처리) */
    public Page<VolunteerWorkActivity> findMyVolunteerById(Pageable pageable, @AuthenticationPrincipal UserDetail userDetail);
}
