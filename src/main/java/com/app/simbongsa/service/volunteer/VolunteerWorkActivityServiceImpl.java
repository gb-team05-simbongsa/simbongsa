package com.app.simbongsa.service.volunteer;

import com.app.simbongsa.domain.RicePaymentDTO;
import com.app.simbongsa.domain.SupportDTO;
import com.app.simbongsa.domain.VolunteerWorkActivityDTO;
import com.app.simbongsa.domain.VolunteerWorkDTO;
import com.app.simbongsa.entity.rice.RicePayment;
import com.app.simbongsa.entity.support.Support;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.entity.volunteer.VolunteerWorkActivity;
import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.repository.member.MemberRepository;
import com.app.simbongsa.repository.volunteer.VolunteerWorkActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Qualifier("volunteerWorkActivity") @Primary
public class VolunteerWorkActivityServiceImpl implements VolunteerWorkActivityService {
    private final VolunteerWorkActivityRepository volunteerWorkActivityRepository;
    private final MemberRepository memberRepository;

    @Override
    public Page<VolunteerWorkActivityDTO> getVolunteerWorkActivity(Long id, Integer page) {
        Page<VolunteerWorkActivity> volunteerWorkActivities = volunteerWorkActivityRepository.findApplyByVolunteerWorkId(id, PageRequest.of(page, 5));
        List<VolunteerWorkActivityDTO> volunteerWorkActivityDTOS = volunteerWorkActivities.getContent().stream().map(this::toVolunteerWorkActivityDTO).collect(Collectors.toList());
        return new PageImpl<>(volunteerWorkActivityDTOS, volunteerWorkActivities.getPageable(), volunteerWorkActivities.getTotalElements());
    }

    /* 내 봉사 활동 목록 조회(페이징처리) */
    @Override
    public Page<VolunteerWorkActivityDTO> getMyVolunteerWork(Integer page, Long memberId) {
        Page<VolunteerWorkActivity> myVolunteerWorkActivity = volunteerWorkActivityRepository.findMyVolunteerById(PageRequest.of(0, 5), memberId);
        List<VolunteerWorkActivityDTO> volunteerWorkActivityDTOS = myVolunteerWorkActivity.getContent().stream().map(this::toVolunteerWorkActivityDTO).collect(Collectors.toList());
        return new PageImpl<>(volunteerWorkActivityDTOS, myVolunteerWorkActivity.getPageable(),myVolunteerWorkActivity.getTotalElements());
    }

    @Override
    public void saveVolunteerWorkActivity(VolunteerWorkActivityDTO volunteerWorkActivityDTO) {
        volunteerWorkActivityRepository.save(toVolunteerWorkActivityEntity(volunteerWorkActivityDTO));
    }



}
