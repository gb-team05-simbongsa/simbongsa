package com.app.simbongsa.controller;

import com.app.simbongsa.domain.InquiryDTO;
import com.app.simbongsa.domain.NoticeDTO;
import com.app.simbongsa.domain.PageDTO;
import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.search.admin.AdminNoticeSearch;
import com.app.simbongsa.service.inquiry.InquiryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/*")
@RequiredArgsConstructor
@Slf4j
public class MypageController {
    private final InquiryService inquiryService;

    /* 유저아이디로 문의 페이징처리해서 불러오기 */
    @GetMapping("my-question")
    public String notice(Integer page, Model model, @AuthenticationPrincipal UserDetail userDetail) {
        page = page == null ? 0: page - 1;
        Long memberId = userDetail.getId();
        log.info(page + "아이디아이디아이디아이디아이디아이디아이디아이디아이디아이디아이디");

        Page<InquiryDTO> myInquiries = inquiryService.getMyInquiry(page, userDetail);

        PageDTO pageDTO = new PageDTO(myInquiries);

        model.addAttribute("myInquiries", myInquiries.getContent());
        model.addAttribute("pageDTO", pageDTO);
        log.info(pageDTO.getStartPage() + "..............................");
        log.info(pageDTO.getEndPage() + "..............................");
        return "mypage/my-question";
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

    @GetMapping("support-request")
    public String supportRequest(){
        return "mypage/support-request";
    }

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
