package com.app.simbongsa.repository.volunteer;

import com.app.simbongsa.entity.volunteer.VolunteerWorkActivity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class VoluntterActvitiyRepositoryTests {
    @Autowired
    private VolunteerWorkActivityRepository volunteerWorkActivityRepository;

    /* 내 봉사 활동 목록 조회(페이징처리) */
    @Test
    public void findByMemberId() {
        PageRequest pageRequest = PageRequest.of(0,3);
        Page<VolunteerWorkActivity> foundVolunteerWorkActivities = volunteerWorkActivityRepository.findByMemberId(pageRequest, 143L);
        foundVolunteerWorkActivities.stream().map(VolunteerWorkActivity::toString).forEach(log::info);
        log.info("====================유저 아이디 143의  봉사 활동 목록수=================" + foundVolunteerWorkActivities.getTotalElements());
    }
}
