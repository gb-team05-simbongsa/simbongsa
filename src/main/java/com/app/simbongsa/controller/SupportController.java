package com.app.simbongsa.controller;

import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.domain.PageDTO;
import com.app.simbongsa.domain.SupportDTO;
import com.app.simbongsa.domain.SupportRequestDTO;
import com.app.simbongsa.entity.support.Support;
import com.app.simbongsa.entity.support.SupportRequest;
import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.repository.support.SupportRequestRepository;
import com.app.simbongsa.service.member.MemberService;
import com.app.simbongsa.service.rice.RicePaymentService;
import com.app.simbongsa.service.support.SupportRequestService;
import com.app.simbongsa.service.support.SupportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @GetMapping("support-detail/{supportRequestId}")
    public String supportDetail(Integer page, Model model, @PathVariable("supportRequestId") Long supportRequestId, @AuthenticationPrincipal UserDetail userDetail){

        Long memberId = userDetail.getId();
        log.info(memberId + "내 로그인한 아이디");

//        후원 총 참여 수
        Long attendCount = supportService.getAllSupportAttend_QueryDSL(supportRequestId);
//        후원 상세페이지 조회
        SupportRequestDTO supportDetail = supportRequestService.getSupportRequestDetail(supportRequestId);
//        로그인한 ID
        MemberDTO memberDTO = memberService.getMemberById(memberId);
        log.info(memberDTO.getMemberRice() + " ================");

//        후원된 공양미
        int totalPrice = supportDetail.getSupports()
                .stream()
                .mapToInt(Support::getSupportPrice)
                .sum();
        int originalPrice = totalPrice * 100;

        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("attendCount", attendCount);
//        model.addAttribute("attendList", attendList.getContent());
//        model.addAttribute("pageDTO", new PageDTO(attendList));
        model.addAttribute("supportDetail", supportDetail);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("originalPrice", originalPrice);
//        등록된 날짜.
        model.addAttribute("createDate", supportDetail.getCreatedDate().toString().substring(0,10));
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
