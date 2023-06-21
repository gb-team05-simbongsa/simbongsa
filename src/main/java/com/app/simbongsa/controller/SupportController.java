package com.app.simbongsa.controller;

import com.app.simbongsa.domain.*;
import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.repository.member.MemberRepository;
import com.app.simbongsa.repository.rice.RicePaymentRepository;
import com.app.simbongsa.repository.support.SupportRepository;
import com.app.simbongsa.repository.support.SupportRequestRepository;
import com.app.simbongsa.service.member.MemberService;
import com.app.simbongsa.service.rice.RicePaymentService;
import com.app.simbongsa.service.support.SupportRequestService;
import com.app.simbongsa.service.support.SupportService;
import com.app.simbongsa.type.RicePaymentType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/support/*")
@RequiredArgsConstructor
@Slf4j
public class SupportController {
    private final SupportRequestService supportRequestService;
    private final SupportService supportService;
    private final RicePaymentService ricePaymentService;
    private final MemberService memberService;

    @PostMapping("attend-member")
    @ResponseBody
    public Page<SupportDTO> attendMember(Long id, @RequestParam(value = "page") Integer page){
        log.info("들어오나?");
        return supportService.getAllSupportAttendWithMember_QueryDSL(page, id);
    }

//    참여내역 페이징 처리
    @GetMapping("support-detail/{supportRequestId}")
    public String supportDetail(Integer page, Model model, HttpSession httpSession, @PathVariable("supportRequestId") Long supportRequestId){
        MemberDTO memberDTO = (MemberDTO)httpSession.getAttribute("member");
        log.info("들어오나?");
        page = page == null ? 0 : page -1;
//        Long memberId = userDetail.getMember().getId();
        if(memberDTO != null) {
            Long memberId = memberDTO.getId();
            log.info(memberId + "내 로그인한 아이디");
            //        로그인한 ID
            MemberDTO member = memberService.getMemberById(memberId);
            model.addAttribute("memberDTO", member);
        }

//        후원 총 참여 수
        Long attendCount = supportService.getAllSupportAttend_QueryDSL(supportRequestId);
//        후원 상세페이지 조회
        SupportRequestDTO supportDetail = supportRequestService.getSupportRequestDetail(supportRequestId);
//        로그인한 ID

//        후원된 공양미
        int totalPrice = supportDetail.getSupportDTOS()
                .stream()
                .mapToInt(SupportDTO::getSupportPrice)
                .sum();
        int originalPrice = totalPrice * 100;

        model.addAttribute("attendCount", attendCount);
        model.addAttribute("supportRequestDTO", supportDetail);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("originalPrice", originalPrice);
//        등록된 날짜.
        model.addAttribute("createDate", supportDetail.getCreatedDate().toString().substring(0,10));
        return "support/support-detail";

    }
    @PostMapping("support")
    public RedirectView support(String supportRequestId, Integer supportAmount, HttpSession session){
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        SupportRequestDTO supportRequest = supportRequestService.getSupportRequestDetail(Long.valueOf(supportRequestId));
        MemberDTO receiveMemberDTO = supportRequest.getMemberDTO();

        SupportDTO supportDTO = new SupportDTO();
        supportDTO.setSupportRequestDTO(supportRequest);
        supportDTO.setMemberDTO(memberDTO);
        supportDTO.setSupportPrice(supportAmount);

        ricePaymentService.insertSupportRicePayment(supportAmount, memberDTO, RicePaymentType.사용);
        supportService.saveSupport(supportDTO, memberDTO.getId());
        ricePaymentService.insertSupportRicePayment(supportAmount, receiveMemberDTO, RicePaymentType.후원받은공양미);
        supportService.updateRice(-supportAmount, memberDTO.getId());
        supportService.updateRice(supportAmount, receiveMemberDTO.getId());

        return new RedirectView("/support/support-success");
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

    /*============== 후원 성공 시 이동 페이지 ==============*/
    @GetMapping("support-success")
    public String supportSuccess(){

        return "support/support-success";
    }

    /*============== 후원 요청 페이지 ==============*/
    @PostMapping("support-write")
    @ResponseBody
    public void supportWrite(@RequestBody SupportRequestDTO supportRequestDTO, HttpSession httpSession){
        MemberDTO memberDTO = (MemberDTO)httpSession.getAttribute("member");
        supportRequestDTO.setMemberDTO(memberDTO);
        supportRequestService.register(supportRequestDTO);
    }
    @GetMapping("support-write")
    public void goToWriteForm(){
    }

}
