package com.app.simbongsa.controller;

import com.app.simbongsa.domain.*;
import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.search.admin.AdminBoardSearch;
import com.app.simbongsa.search.admin.AdminNoticeSearch;
import com.app.simbongsa.service.inquiry.AnswerService;
import com.app.simbongsa.service.inquiry.InquiryService;
import com.app.simbongsa.service.support.SupportRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mypage/*")
@RequiredArgsConstructor
@Slf4j
public class MypageController {
    private final InquiryService inquiryService;
    private final AnswerService answerService;
    private final SupportRequestService supportRequestService;

    /* 내 문의 페이징처리해서 불러오기 */
    @GetMapping("my-question")
    public String notice(Integer page, Model model, @AuthenticationPrincipal UserDetail userDetail) {
        page = page == null ? 0 : page - 1;
        Page<InquiryDTO> myInquiries = inquiryService.getMyInquiry(page, userDetail);

        for (InquiryDTO myInquiry: myInquiries.getContent()) {
            AnswerDTO answerAboutInquiry = answerService.findByInquiryId(myInquiry.getId());
            /*log.info(answerAboutInquiry + "%%%%%%%%%%%5문의에 대한 답변%%%%%%%%%%%%%");*/
            myInquiry.setAnswerDTO(answerAboutInquiry);
        }

        model.addAttribute("myInquiries", myInquiries.getContent());
        model.addAttribute("pageDTO", new PageDTO(myInquiries));
        return "mypage/my-question";
    }

    /* 내 후원요청목록 페이징처리해서 불러오기 */
    @GetMapping("support-request")
    public String supportRequest(Integer page, Model model, @AuthenticationPrincipal UserDetail userDetail){
        Long memberId = userDetail.getId();
        log.info(memberId + "아이디아이디아이디아이디아이디아이디아이디아이디아이디아이디아이디");
        page = page == null ? 0 : page - 1;
        Page<SupportRequestDTO> mySupportRequests = supportRequestService.getMySupportRequest(page, userDetail);

        model.addAttribute("mySupportRequests", mySupportRequests.getContent());
        model.addAttribute("pageDTO",new PageDTO(mySupportRequests));
        return "mypage/support-request";
    }

    @GetMapping("exchange-request")
    public String exchangeRequest(){
        return "mypage/exchange-request";
    }

    @GetMapping("my-funding-list")
    public String myFundingList(){
        return "mypage/my-funding-list";
    }

/*    @GetMapping("my-question")
    public String myQuestion(){
        return "mypage/my-question";
    }*/

    @GetMapping("my-review")
    public String myReview(){
        return "mypage/my-review";
    }

    @GetMapping("my-support-list")
    public String mySupportList(){
        return "mypage/my-support-list";
    }

    @GetMapping("rice-charge")
    public String riceCharge(){
        return "mypage/rice-charge";
    }

    @GetMapping("rice-list")
    public String riceList(){
        return "mypage/rice-list";
    }

/*    @GetMapping("support-request")
    public String supportRequest(){
        return "mypage/support-request";
    }*/

    @GetMapping("user-modify")
    public String userModify(){
        return "mypage/user-modify";
    }

    @GetMapping("volunteer-work-list")
    public String volunteerWorkList(){
        return "mypage/volunteer-work-list";
    }

    @GetMapping("withdraw-check")
    public String withdrawCheck(){
        return "mypage/withdraw-check";
    }

    @GetMapping("withdraw-login")
    public String withdrawLogin(){
        return "mypage/withdraw-login";
    }
}
