package com.app.simbongsa.controller;


import com.app.simbongsa.domain.VolunteerWorkDTO;
import com.app.simbongsa.service.volunteer.VolunteerWorkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/volunteer-work/*")
@RequiredArgsConstructor
@Slf4j
public class VolunteerWorkController {
    private VolunteerWorkService volunteerWorkService;
    @GetMapping("work-list")
    public String workList() { return "volunteer-work/work";}

    @GetMapping("work-detail")
    public String workDetail(Model model/*, @PathVariable("volunteerId") Long volunteerId*/) {
//        VolunteerWorkDTO volunteerWorkDetail = volunteerWorkService.getVolunteerWorkDetail(volunteerId);

//        model.addAttribute("volunteerWorkDetail", volunteerWorkDetail);
        return "volunteer-work/work-detail";
    }

    @GetMapping("work-search")
    public String workSearch() { return "volunteer-work/work-search2";}

    @GetMapping("work-modal")
    public String workModal() { return "volunteer-work/work-apply-modal";}

}
