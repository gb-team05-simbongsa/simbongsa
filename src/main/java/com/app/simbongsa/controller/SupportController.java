package com.app.simbongsa.controller;

import com.app.simbongsa.domain.*;
import com.app.simbongsa.service.member.MemberService;
import com.app.simbongsa.service.rice.RicePaymentService;
import com.app.simbongsa.service.support.SupportRequestService;
import com.app.simbongsa.service.support.SupportService;
import com.app.simbongsa.type.RicePaymentType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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

    /*============== 후원 상세페이지 및 페이징 처리==============*/
//    참여 내역 페이징
    @PostMapping("attend-member")
    @ResponseBody
    public Page<SupportDTO> attendMember(Long id, @RequestParam(value = "page") Integer page){
        return supportService.getAllSupportAttendWithMember_QueryDSL(page, id);
    }
//    상세페이지
    @GetMapping("support-detail/{supportRequestId}")
    public String supportDetail(Integer page, Model model, HttpSession httpSession, @PathVariable("supportRequestId") Long supportRequestId){
        MemberDTO memberDTO = (MemberDTO)httpSession.getAttribute("member");
        page = page == null ? 0 : page -1;
        if(memberDTO != null) {
//          로그인 한 정보
            Long memberId = memberDTO.getId();
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
//         해당 게시물의 총 참여자수
        model.addAttribute("attendCount", attendCount);
//         supportRequestDTO
        model.addAttribute("supportRequestDTO", supportDetail);
//         해당 게시물에 후원 된 공양미의 수
        model.addAttribute("totalPrice", totalPrice);
//         해당 게시물의 후원 된  원래 금액 = 공양미 * 100
        model.addAttribute("originalPrice", originalPrice);
//        등록된 날짜
        model.addAttribute("createDate", supportDetail.getCreatedDate().toString().substring(0,10));
        return "support/support-detail";

    }
//    해당 게시물에 후원하기
    @PostMapping("support")
    public RedirectView support(String supportRequestId, Integer supportAmount, HttpSession session){
//        세션으로 멤버의 정보를 가져옴(로그인한 정보)
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
//        supportRequest의 상세정보를 저장
        SupportRequestDTO supportRequest = supportRequestService.getSupportRequestDetail(Long.valueOf(supportRequestId));
//        후원을 받는 회원 정보 가져오기
        MemberDTO receiveMemberDTO = supportRequest.getMemberDTO();

//        SupportDTO 객체 생성 및 정보 설정
        SupportDTO supportDTO = new SupportDTO();
        supportDTO.setSupportRequestDTO(supportRequest);
        supportDTO.setMemberDTO(memberDTO);
        supportDTO.setSupportPrice(supportAmount);
//        공양미 사용 결제 처리
        ricePaymentService.insertSupportRicePayment(supportAmount, memberDTO, RicePaymentType.사용);
//        후원 정보 저장 및 공양미 업데이트
        supportService.saveSupport(supportDTO, memberDTO.getId());
        ricePaymentService.insertSupportRicePayment(supportAmount, receiveMemberDTO, RicePaymentType.후원받은공양미);
        supportService.updateRice(-supportAmount, memberDTO.getId());
        supportService.updateRice(supportAmount, receiveMemberDTO.getId());

        return new RedirectView("/support/support-success");
    }


    /*============== 후원 목록페이지 페이징처리 ==============*/
    @GetMapping("support-list")
    public String supportList(Integer page, Model model, String keyword){
        if(keyword == null){
            keyword = "";
        }
        // 페이지가 null인 경우 0으로 설정하고, 아닌 경우에는 1을 뺀 값을 사용
        page = page == null ? 0 : page -1;

        // 지정된 페이지와 키워드를 사용하여 SupportRequestDTO의 paging 가져옴
        Page<SupportRequestDTO> supportRequestDTOs = supportRequestService.getSupportRequestAllWithPaging_QueryDSL(page, keyword);

        // 모델에 SupportRequestDTO 목록과 페이지 정보를 추가
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
