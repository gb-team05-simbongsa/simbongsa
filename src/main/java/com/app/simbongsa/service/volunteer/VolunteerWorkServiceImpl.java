package com.app.simbongsa.service.volunteer;

import com.app.simbongsa.domain.VolunteerWorkDTO;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.repository.volunteer.VolunteerWorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Qualifier("volunteerWork") @Primary
public class VolunteerWorkServiceImpl implements VolunteerWorkService {
    private final VolunteerWorkRepository volunteerWorkRepository;

    /*메인페이지 - 봉사 활동 8개 띄우기*/
    @Override
    public List<VolunteerWorkDTO> getVolunteerList() {
        List<VolunteerWork> volunteerWorks = volunteerWorkRepository.findVolunteerWorkList();
        List<VolunteerWorkDTO> volunteerWorkDTOS = new ArrayList<>();
        for (VolunteerWork volunteerWork : volunteerWorks) {
            VolunteerWorkDTO volunteerWorkDTO = toVolunteerWorkDTO(volunteerWork);
            volunteerWorkDTOS.add(volunteerWorkDTO);
        }
        return volunteerWorkDTOS;
        }
    }

