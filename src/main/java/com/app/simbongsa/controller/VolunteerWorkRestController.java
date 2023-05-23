package com.app.simbongsa.controller;

import com.app.simbongsa.domain.VolunteerWorkDTO;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.service.volunteer.VolunteerWorkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/volunteers/*")
@RequiredArgsConstructor
@Slf4j
public class VolunteerWorkRestController {
    private final VolunteerWorkService volunteerWorkService;

}
