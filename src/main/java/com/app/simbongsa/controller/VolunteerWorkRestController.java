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

    @GetMapping("work-list")
    @ResponseBody
    public Slice<VolunteerWorkDTO> getVolunteerList(@RequestParam(defaultValue = "0", name = "page") int page, @RequestParam(defaultValue = "전체보기", name = "keyword") String keyword){
        PageRequest pageRequest = PageRequest.of(page, 12);
        return volunteerWorkService.getAllScorollAndSearch(keyword,pageRequest);
    }


}
