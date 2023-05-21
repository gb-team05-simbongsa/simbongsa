package com.app.simbongsa.repository.volunteer;

import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.entity.volunteer.VolunteerWorkActivity;
import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.repository.member.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@Transactional
@Rollback(false)
@Slf4j
public class VolunteerActvitiyRepositoryTests {
    @Autowired
    private VolunteerWorkActivityRepository volunteerWorkActivityRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private VolunteerWorkRepository volunteerWorkRepository;
    /* 내 봉사 활동 목록 조회(페이징처리) */


    @Test
    public void saveTest(){
        for (int i = 0; i < 18; i++) {
        Member member = memberRepository.findById(1L).get();
        VolunteerWork volunteerWork = volunteerWorkRepository.findById(773L).get();
        VolunteerWorkActivity volunteerWorkActivity = new VolunteerWorkActivity(LocalDate.now(), member, volunteerWork);
            volunteerWorkActivityRepository.save(volunteerWorkActivity);
        }
    }
    @Test
    public void findByMemberId() {
        /*PageRequest pageRequest = PageRequest.of(0,3);
        UserDetail userDetail = new UserDetail();
        userDetail.setId(561L);
        Page<VolunteerWorkActivity> foundVolunteerWorkActivities = volunteerWorkActivityRepository.findMyVolunteerById(pageRequest, userDetail);
        foundVolunteerWorkActivities.stream().map(VolunteerWorkActivity::toString).forEach(log::info);
        log.info("====================유저 아이디 143의  봉사 활동 목록수=================" + foundVolunteerWorkActivities.getTotalElements());*/
    }
}
