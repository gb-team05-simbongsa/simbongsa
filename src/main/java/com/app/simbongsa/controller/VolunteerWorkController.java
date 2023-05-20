package com.app.simbongsa.controller;


import com.app.simbongsa.domain.VolunteerWorkDTO;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.repository.volunteer.VolunteerWorkRepository;
import com.app.simbongsa.service.volunteer.VolunteerWorkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/volunteer-work/*")
@RequiredArgsConstructor
@Slf4j
public class VolunteerWorkController {

    private final VolunteerWorkService volunteerWorkService;

    @GetMapping("work-detail/{volunteerWorkId}")
    public String workDetail(Model model, @PathVariable("volunteerWorkId") Long volunteerWorkId) {
        VolunteerWorkDTO volunteerWorkDetail = volunteerWorkService.getVolunteerWorkDetail(volunteerWorkId);

        model.addAttribute("volunteerWorkDetail", volunteerWorkDetail);
        return "volunteer-work/work-detail";
    }

    @GetMapping("work-search")
    public String workSearch() { return "volunteer-work/work-search2";}

    @GetMapping("work-modal")
    public String workModal() { return "volunteer-work/work-apply-modal";}
    @GetMapping("list")
    public String volunteerList(){
        return "volunteer-work/work";
    }

}
