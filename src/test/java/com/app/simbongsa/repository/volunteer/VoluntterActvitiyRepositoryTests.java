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
public class VoluntterActvitiyRepositoryTests {
    @Autowired
    private VolunteerWorkActivityRepository volunteerWorkActivityRepository;
    private MemberRepository memberRepository;
    private VolunteerWorkRepository volunteerWorkRepository;
    /* 내 봉사 활동 목록 조회(페이징처리) */
    @Test
    public void saveTest(){

        Optional<Member> memberOptional = memberRepository.findById(595L);
        Optional<VolunteerWork> volunteerWorkOptional = volunteerWorkRepository.findById(197L);

        memberOptional.ifPresent(member -> {
            volunteerWorkOptional.ifPresent(volunteerWork -> {
                VolunteerWorkActivity volunteerWorkActivity = new VolunteerWorkActivity(
                        LocalDate.now(),
                        member,
                        volunteerWork
                );
                volunteerWorkActivityRepository.save(volunteerWorkActivity);

                // 테스트에 필요한 동작 수행
            });
        });

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
