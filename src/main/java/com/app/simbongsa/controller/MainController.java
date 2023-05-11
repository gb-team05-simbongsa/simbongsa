package com.app.simbongsa.controller;

import com.app.simbongsa.domain.VolunteerWorkDTO;
import com.app.simbongsa.service.volunteer.VolunteerWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/main/*")
@RequiredArgsConstructor
public class MainController {
    private final VolunteerWorkService volunteerWorkService;

    @GetMapping("")
    public String main(Model model) {
        List<VolunteerWorkDTO> volunteerList = volunteerWorkService.getVolunteerList();
        model.addAttribute("volunteerList", volunteerList);
        return "main/main";
    }
}
