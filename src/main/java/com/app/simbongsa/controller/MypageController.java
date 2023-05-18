package com.app.simbongsa.controller;

import com.app.simbongsa.domain.*;
import com.app.simbongsa.entity.board.FreeBoard;
import com.app.simbongsa.entity.rice.RicePayment;
import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.repository.board.FreeBoardRepository;
import com.app.simbongsa.repository.rice.RicePaymentRepository;
import com.app.simbongsa.search.admin.AdminBoardSearch;
import com.app.simbongsa.search.admin.AdminNoticeSearch;
import com.app.simbongsa.service.board.FreeBoardService;
import com.app.simbongsa.service.inquiry.AnswerService;
import com.app.simbongsa.service.inquiry.InquiryService;
import com.app.simbongsa.service.member.MemberService;
import com.app.simbongsa.service.rice.RicePaymentService;
import com.app.simbongsa.service.support.SupportRequestService;
import com.app.simbongsa.service.volunteer.VolunteerWorkActivityService;
import com.app.simbongsa.service.volunteer.VolunteerWorkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.xml.ws.Service;
import java.util.List;

@Controller
@RequestMapping("/mypage/*")
@RequiredArgsConstructor
@Slf4j
public class MypageController {
    private final InquiryService inquiryService;
    private final AnswerService answerService;
    private final SupportRequestService supportRequestService;
    private final FreeBoardService freeBoardService;
    private final FreeBoardRepository freeBoardRepository;
    private final MemberService memberService;
    private final RicePaymentService ricePaymentService;
    private final VolunteerWorkActivityService volunteerWorkActivityService;

    /* 내 문의 페이징처리해서 불러오기 */
    @GetMapping("my-question")
    public String notice(Integer page, Model model, @AuthenticationPrincipal UserDetail userDetail) {
        page = page == null ? 0 : page - 1;
        Page<InquiryDTO> myInquiries = inquiryService.getMyInquiry(page, userDetail);

        log.info(myInquiries.toString() + "asdfasaaaaaaaddddddddddddddddddd");

        model.addAttribute("myInquiries", myInquiries.getContent());
        model.addAttribute("pageDTO", new PageDTO(myInquiries));
        return "mypage/my-question";
    }

    /* 내 문의 게시글 수정 */
    @PostMapping("my-question-update")
    public RedirectView myQuestionUpdate(Long id, String inquiryTitle, String inquiryContent, @AuthenticationPrincipal UserDetail userDetail) {
        InquiryDTO inquiryDTO = InquiryDTO.builder().id(id).inquiryTitle(inquiryTitle).inquiryContent(inquiryContent).memberDTO(memberService.getMemberById(userDetail.getId())).build();
        inquiryService.setInquiry(inquiryDTO);
        log.info("문의 게시글 수정에서의 inquiryDTO: ", inquiryDTO.toString());
        return new RedirectView("my-question");
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

    /* 내 자유 게시글 목록 */
    @GetMapping("my-review")
    public String myReview(Integer page, Model model, @AuthenticationPrincipal UserDetail userDetail){
        Long memberId = userDetail.getId();
        log.info(memberId + "아이디아이디아이디아이디아이디아이디아이디아이디아이디아이디아이디");
        page = page == null ? 0 : page - 1;
        log.info(page + "pagepagepagepapgapgagpdspagpsdgpasdpgapsppage");
        Page<FreeBoard> myFreeB = freeBoardRepository.findByMemberId(PageRequest.of(page, 5), userDetail);
        log.info(myFreeB + "이거 나온망로 ㅏㅇㅁ널이ㅓ ㅁ니;ㅏ어ㅏ림ㄴㅇㄻㅇㄴㄻㄴㅇㄻㅇㄻㅇㄴㄹㄴㅇㅁ");
        Page<FreeBoardDTO> myFreeBoards = freeBoardService.getMyFreeBoards(page, userDetail);

        model.addAttribute("myFreedBoards", myFreeBoards.getContent());
        model.addAttribute("pageDTO", new PageDTO(myFreeBoards));
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

    /* 내 공양미 조회(페이징) */
    /*public String notice(Integer page, Model model, @AuthenticationPrincipal UserDetail userDetail) {
        page = page == null ? 0 : page - 1;
        Page<InquiryDTO> myInquiries = inquiryService.getMyInquiry(page, userDetail);

        log.info(myInquiries.toString() + "asdfasaaaaaaaddddddddddddddddddd");

        model.addAttribute("myInquiries", myInquiries.getContent());
        model.addAttribute("pageDTO", new PageDTO(myInquiries));
        return "mypage/my-question";
    }*/

    @GetMapping("rice-list")
    public String riceList(Integer page, Model model, @AuthenticationPrincipal UserDetail userDetail){
        page = page == null ? 0 : page - 1;
        Page<RicePaymentDTO> myRice = ricePaymentService.getMyRicePayment(page, userDetail);

        log.info(myRice.toString() + "------------내 공양미 리스트-------------- ");
        model.addAttribute("myRicePayments", myRice.getContent());
        model.addAttribute("pageDTO", new PageDTO(myRice));
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
    public String volunteerWorkList(Integer page, Model model, @AuthenticationPrincipal UserDetail userDetail){
        page = page == null ? 0 : page - 1;
        Page<VolunteerWorkActivityDTO> myActivity = volunteerWorkActivityService.getMyVolunteerWork(page, userDetail);
        model.addAttribute("myActivity",myActivity.getContent());
        model.addAttribute("pageDTO",new PageDTO(myActivity));
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
