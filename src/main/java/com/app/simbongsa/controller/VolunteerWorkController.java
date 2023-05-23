package com.app.simbongsa.controller;


import com.app.simbongsa.domain.*;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.repository.member.MemberRepository;
import com.app.simbongsa.repository.volunteer.VolunteerWorkRepository;
import com.app.simbongsa.search.admin.AdminPaymentSearch;
import com.app.simbongsa.service.member.MemberService;
import com.app.simbongsa.service.volunteer.VolunteerWorkActivityService;
import com.app.simbongsa.service.volunteer.VolunteerWorkService;
import com.app.simbongsa.type.RicePaymentType;
import com.app.simbongsa.type.VolunteerWorkCategoryType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/volunteer-work/*")
@RequiredArgsConstructor
@Slf4j
public class VolunteerWorkController {

    private final VolunteerWorkService volunteerWorkService;
    private final MemberService memberService;
    private final VolunteerWorkActivityService volunteerWorkActivityService;

    @GetMapping("work-detail/{volunteerWorkId}")
    public String workDetail(Model model, @PathVariable("volunteerWorkId") Long volunteerWorkId, HttpSession httpSession) {
        MemberDTO memberDTO = (MemberDTO)httpSession.getAttribute("member");
        VolunteerWorkDTO volunteerWorkDetail = volunteerWorkService.getVolunteerWorkDetail(volunteerWorkId);
        MemberDTO memberInfo = memberService.getMemberById(memberDTO.getId());
        log.info(memberInfo + "==========================");
        log.info(volunteerWorkDetail + "==========================");


        model.addAttribute("memberInfo", memberInfo);
        model.addAttribute("volunteerWorkDetail", volunteerWorkDetail);
        return "volunteer-work/work-detail";
    }
    @GetMapping("work-detail-modal/{id}")
    public String saveWorkDetailModal(Model model, @PathVariable("id") Long id, HttpSession httpSession, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate volunteerWorkActivityDate){
        MemberDTO memberDTO = (MemberDTO)httpSession.getAttribute("member");
        VolunteerWorkDTO volunteerWorkDetail = volunteerWorkService.getVolunteerWorkDetail(id);
        log.info(volunteerWorkDetail+ " ==========");
        MemberDTO memberInfo = memberService.getMemberById(memberDTO.getId());
        VolunteerWorkActivityDTO volunteerWorkActivityDTO = new VolunteerWorkActivityDTO();
        volunteerWorkActivityDTO.setMemberDTO(memberDTO);
        volunteerWorkActivityDTO.setVolunteerWorkDTO(volunteerWorkDetail);
        volunteerWorkActivityDTO.setVolunteerWorkActivityDate(volunteerWorkActivityDate);
        volunteerWorkActivityService.saveVolunteerWorkActivity(volunteerWorkActivityDTO);

        model.addAttribute("memberInfo", memberInfo);
        model.addAttribute("volunteerWorkDetail", volunteerWorkDetail);
        return "volunteer-work/work-detail";
    }

    @GetMapping("work-list")
    public String volunteerList(Integer page, @RequestParam(required = false) String keyword, Model model) {
        page = page == null ? 0 : page - 1;
        String volunteerWorkType = null;
        log.info(volunteerWorkType + "============================");
        if(keyword == null){
            keyword = "";
        }
        if(volunteerWorkType == null){
            volunteerWorkType = "";
        }

        log.info(volunteerWorkType + "============================");
        Page<VolunteerWorkDTO> volunteerWorkDTOS = volunteerWorkService.pagingVolunteerWork(keyword, page, volunteerWorkType);
        Long total = volunteerWorkDTOS.getTotalElements();
        model.addAttribute("total", total);
        model.addAttribute("volunteerWorkDTOS", volunteerWorkDTOS.getContent());
        model.addAttribute("pageDTO", new PageDTO(volunteerWorkDTOS));
        model.addAttribute("keyword", keyword);

        return "volunteer-work/work-list";
    }

    @GetMapping("work-list-category/{volunteerWorkType}")
    public String volunteerCategory(Model model, Integer page, @RequestParam(required = false) String keyword, @PathVariable("volunteerWorkType") String volunteerWorkCategoryType){
        page = page == null ? 0 : page - 1;
        if(keyword == null){
            keyword = "";
        }
        if(volunteerWorkCategoryType == null){
            volunteerWorkCategoryType = "";
        }
        Page<VolunteerWorkDTO> volunteerWorkDTOS = volunteerWorkService.pagingVolunteerWork(keyword, page, volunteerWorkCategoryType);

        model.addAttribute("volunteerWorkDTOS", volunteerWorkDTOS.getContent());
        Long total = volunteerWorkDTOS.getTotalElements();
        model.addAttribute("total", total);
        model.addAttribute("pageDTO", new PageDTO(volunteerWorkDTOS));

        return "volunteer-work/work-list";
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
