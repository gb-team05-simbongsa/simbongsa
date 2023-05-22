package com.app.simbongsa.controller;


import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.domain.VolunteerWorkDTO;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.repository.member.MemberRepository;
import com.app.simbongsa.repository.volunteer.VolunteerWorkRepository;
import com.app.simbongsa.service.member.MemberService;
import com.app.simbongsa.service.volunteer.VolunteerWorkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/volunteer-work/*")
@RequiredArgsConstructor
@Slf4j
public class VolunteerWorkController {

    private final VolunteerWorkService volunteerWorkService;
    private final MemberService memberService;

    @GetMapping("work-detail/{volunteerWorkId}")
    public String workDetail(Model model, @PathVariable("volunteerWorkId") Long volunteerWorkId, HttpSession httpSession) {
        MemberDTO memberDTO = (MemberDTO)httpSession.getAttribute("member");
        VolunteerWorkDTO volunteerWorkDetail = volunteerWorkService.getVolunteerWorkDetail(volunteerWorkId);
        MemberDTO memberInfo = memberService.getMemberById(memberDTO.getId());

        model.addAttribute("memberInfo", memberInfo);
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
