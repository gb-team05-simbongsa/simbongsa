package com.app.simbongsa.service.volunteer;

import com.app.simbongsa.entity.volunteer.VolunteerWorkActivity;
import com.app.simbongsa.repository.volunteer.VolunteerWorkActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Qualifier("volunteerWorkActivity") @Primary
public class VolunteerWorkActivityServiceImpl implements VolunteerWorkActivityService {
    private final VolunteerWorkActivityRepository volunteerWorkActivityRepository;

    @Override
    public List<VolunteerWorkActivity> getVolunteerWorkActivity(Long id) {
        return volunteerWorkActivityRepository.findApplyByVolunteerWorkId(id);
    }
}
