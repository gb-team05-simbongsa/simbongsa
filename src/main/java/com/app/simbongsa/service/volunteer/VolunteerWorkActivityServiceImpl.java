package com.app.simbongsa.service.volunteer;

import com.app.simbongsa.domain.RicePaymentDTO;
import com.app.simbongsa.domain.VolunteerWorkActivityDTO;
import com.app.simbongsa.domain.VolunteerWorkDTO;
import com.app.simbongsa.entity.rice.RicePayment;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.entity.volunteer.VolunteerWorkActivity;
import com.app.simbongsa.provider.UserDetail;
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

    @Override
    public List<VolunteerWorkActivity> getVolunteerWorkActivity(Long id) {
        return volunteerWorkActivityRepository.findApplyByVolunteerWorkId(id);
    }

    /* 내 봉사 활동 목록 조회(페이징처리) */
    @Override
    public Page<VolunteerWorkActivityDTO> getMyVolunteerWork(Integer page, UserDetail userDetail) {
        Page<VolunteerWorkActivity> myVolunteerWorkActivity = volunteerWorkActivityRepository.findMyVolunteerById(PageRequest.of(0, 5), userDetail);
        List<VolunteerWorkActivityDTO> volunteerWorkActivityDTOS = myVolunteerWorkActivity.getContent().stream().map(this::toVolunteerWorkActivityDTO).collect(Collectors.toList());
        return new PageImpl<>(volunteerWorkActivityDTOS, myVolunteerWorkActivity.getPageable(),myVolunteerWorkActivity.getTotalElements());
    }
}
