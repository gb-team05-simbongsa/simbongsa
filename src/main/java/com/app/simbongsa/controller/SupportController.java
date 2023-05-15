package com.app.simbongsa.controller;

import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.domain.PageDTO;
import com.app.simbongsa.domain.SupportDTO;
import com.app.simbongsa.domain.SupportRequestDTO;
import com.app.simbongsa.entity.support.SupportRequest;
import com.app.simbongsa.repository.support.SupportRequestRepository;
import com.app.simbongsa.service.member.MemberService;
import com.app.simbongsa.service.rice.RicePaymentService;
import com.app.simbongsa.service.support.SupportRequestService;
import com.app.simbongsa.service.support.SupportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/support/*")
@RequiredArgsConstructor
@Slf4j
public class SupportController {
    private final SupportRequestService supportRequestService;
    private final SupportService supportService;
    private final RicePaymentService ricePaymentService;
    private final MemberService memberService;

//    참여내역 페이징 처리
    @GetMapping("support-detail")
    public String supportDetail(/*Integer page, Model model*//*, @PathVariable("supportRequestId") Long supportRequestId, Long memberId*/){
//        page = page == null ? 0 : page -1;
//        Page<SupportDTO> attendList = supportService.getAllSupportAttendWithMember_QueryDSL(page, supportRequestId);
//        Long attendCount = supportService.getAllSupportAttend_QueryDSL(supportRequestId);
//        SupportRequestDTO getSupportRequestDetail = supportRequestService.getSupportRequestDetail(supportRequestId);
//        MemberDTO memberRice = memberService.getMemberById(memberId);
//
//        model.addAttribute("memberRice", memberRice);
//        model.addAttribute("attendCount", attendCount);
//        model.addAttribute("supportDTOS", attendList);
//        model.addAttribute("pageDTO", new PageDTO(attendList));
//        model.addAttribute("getSupportRequestDetail", getSupportRequestDetail);
        return "support/support-detail";
    }


//    후원 목록페이지 페이징처리
    @GetMapping("support-list")
    public String supportList(Integer page, Model model, String keyword){
        log.info(keyword);
        if(keyword == null){
            keyword = "";
        }

        page = page == null ? 0 : page -1;

        Page<SupportRequestDTO> supportRequestDTOs = supportRequestService.getSupportRequestAllWithPaging(page, keyword);

        model.addAttribute("supportRequestDTOs", supportRequestDTOs);
        model.addAttribute("pageDTO", new PageDTO(supportRequestDTOs));
        return "support/support-list";
    }

    @GetMapping("support-success")
    public String supportSuccess(){

        return "support/support-success";
    }
    @GetMapping("support-write")
    public String supportWrite(){
        return "support/support-write";
    }


}
