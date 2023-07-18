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

//    봉사활동 상세페이지
    @GetMapping("work-detail/{volunteerWorkId}")
    public String workDetail(Model model, @PathVariable("volunteerWorkId") Long volunteerWorkId, HttpSession httpSession) {
        MemberDTO memberDTO = (MemberDTO)httpSession.getAttribute("member");
//          특정 자원봉사 활동의 상세정보 가져옴
        VolunteerWorkDTO volunteerWorkDetail = volunteerWorkService.getVolunteerWorkDetail(volunteerWorkId);
        if(memberDTO != null) {
//          로그인한 회원 정보가 세션에 있는 경우 해당 회원 상세정보를 가져옴
            MemberDTO memberInfo = memberService.getMemberById(memberDTO.getId());
            model.addAttribute("memberInfo", memberInfo);
        }
        model.addAttribute("volunteerWorkDetail", volunteerWorkDetail);
        return "volunteer-work/work-detail";
    }
//    봉사활동 신청 모달
    @GetMapping("work-detail-modal/{id}")
    public String saveWorkDetailModal(Model model, @PathVariable("id") Long id, HttpSession httpSession, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate volunteerWorkActivityDate){
        MemberDTO memberDTO = (MemberDTO)httpSession.getAttribute("member");
//        특정 자원봉사 활동의 상세정보 가져옴
        VolunteerWorkDTO volunteerWorkDetail = volunteerWorkService.getVolunteerWorkDetail(id);
//        로그인한 회원의 상세정보 가져옴
        MemberDTO memberInfo = memberService.getMemberById(memberDTO.getId());

//        자원 봉사 활동 정보를 생성하고 저장
        VolunteerWorkActivityDTO volunteerWorkActivityDTO = new VolunteerWorkActivityDTO();
        volunteerWorkActivityDTO.setMemberDTO(memberDTO);
        volunteerWorkActivityDTO.setVolunteerWorkDTO(volunteerWorkDetail);
        volunteerWorkActivityDTO.setVolunteerWorkActivityDate(volunteerWorkActivityDate);
        volunteerWorkActivityService.saveVolunteerWorkActivity(volunteerWorkActivityDTO);

        model.addAttribute("memberInfo", memberInfo);
        model.addAttribute("volunteerWorkDetail", volunteerWorkDetail);
        return "volunteer-work/work-detail";
    }

//    지역별 목록 조회
    @GetMapping("work-list")
    public String volunteerList(Integer page, @RequestParam(required = false) String keyword, Model model) {
        page = page == null ? 0 : page - 1;
//        페이지 및 검색 키워드 설정
        String volunteerWorkType = null;
        if(keyword == null){
            keyword = "";
        }
        if(volunteerWorkType == null){
            volunteerWorkType = "";
        }
//      페이징된 자원봉사 목록 조회
        Page<VolunteerWorkDTO> volunteerWorkDTOS = volunteerWorkService.pagingVolunteerWork(keyword, page, volunteerWorkType);

//       총 자원봉사자 수와 관련 데이터 모델 담아서 보냄
        Long total = volunteerWorkDTOS.getTotalElements();
        model.addAttribute("total", total);
        model.addAttribute("keyword", keyword);
        model.addAttribute("volunteerWorkDTOS", volunteerWorkDTOS.getContent());
        model.addAttribute("pageDTO", new PageDTO(volunteerWorkDTOS));

        return "volunteer-work/work-list";
    }

//    카테고리 타입별 목록 조회
    @GetMapping("work-list/{volunteerWorkType}")
    public String volunteerCategory(Model model, Integer page, @RequestParam(required = false) String keyword, @PathVariable("volunteerWorkType") String volunteerWorkCategoryType){
        page = page == null ? 0 : page - 1;
        if(keyword == null){
            keyword = "";
        }
        if(volunteerWorkCategoryType == null){
            volunteerWorkCategoryType = "";
        }
//        카테고리 타입에 따른 페이징 된 자원봉사 목록 조회
        Page<VolunteerWorkDTO> volunteerWorkDTOS = volunteerWorkService.pagingVolunteerWork(keyword, page, volunteerWorkCategoryType);

        model.addAttribute("volunteerWorkDTOS", volunteerWorkDTOS.getContent());
        Long total = volunteerWorkDTOS.getTotalElements();
        model.addAttribute("total", total);
        model.addAttribute("keyword", keyword);
        model.addAttribute("pageDTO", new PageDTO(volunteerWorkDTOS));

        return "volunteer-work/work-list";
    }

}
