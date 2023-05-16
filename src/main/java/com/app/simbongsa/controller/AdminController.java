package com.app.simbongsa.controller;

import com.app.simbongsa.domain.*;
import com.app.simbongsa.entity.support.SupportRequest;
import com.app.simbongsa.search.admin.*;
import com.app.simbongsa.service.board.FreeBoardService;
import com.app.simbongsa.service.board.ReviewService;
import com.app.simbongsa.service.funding.FundingService;
import com.app.simbongsa.service.inquiry.InquiryService;
import com.app.simbongsa.service.inquiry.NoticeService;
import com.app.simbongsa.service.member.MemberService;
import com.app.simbongsa.service.rice.RicePaymentService;
import com.app.simbongsa.service.support.SupportRequestService;
import com.app.simbongsa.service.volunteer.VolunteerWorkService;
import com.app.simbongsa.type.RicePaymentType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityManager;
import java.util.List;

@Controller
@RequestMapping("/admin/*")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final NoticeService noticeService;
    private final InquiryService inquiryService;
    private final MemberService memberService;
    private final VolunteerWorkService volunteerWorkService;
    private final FundingService fundingService;
    private final RicePaymentService ricePaymentService;
    private final SupportRequestService supportRequestService;
    private final ReviewService reviewService;
    private final FreeBoardService freeBoardService;
    private final PasswordEncoder passwordEncoder;
    private EntityManager entityManager;

//    회원관리 - user.html
//    봉사관리 - volunteer.html
//    문의관리 - inquiry.html
//    공지사항 - notice.html
//    펀딩관리 - funding.html
//    공양미 충전내역 - payment.html
//    공양미 환전요청 - gongyangmi-refund.html
//    후원요청 - sponsorship-request.html
//    활동후기관리 - activity-review-board.html
//    봉사모집자유게시판(자유게시판) - volunteer-recruitment-board.html

    @GetMapping("activity-review-board")
    public String activityReviewBoard(Integer page, Model model) {
        page = page == null ? 0 : page - 1;
        AdminBoardSearch adminBoardSearch = new AdminBoardSearch();
        Page<ReviewDTO> reviewDTOS = reviewService.getReview(page, adminBoardSearch);

        model.addAttribute("reviewDTOS", reviewDTOS.getContent());
        model.addAttribute("pageDTO", new PageDTO(reviewDTOS));
        return "admin/activity-review-board";
    }

    @GetMapping("funding")
    public String funding(Integer page, Model model) {
        page = page == null ? 0 : page - 1;
        AdminFundingSearch adminFundingSearch = new AdminFundingSearch();
        Page<FundingDTO> fundingDTOS = fundingService.getFunding(page, adminFundingSearch);

        model.addAttribute("fundingDTOS", fundingDTOS.getContent());
        model.addAttribute("pageDTO", new PageDTO(fundingDTOS));
        return "admin/funding";
    }

    @GetMapping("gongyangmi-refund")
    public String gongyangmiRefund(Integer page, Model model) {
        page = page == null ? 0 : page - 1;
        AdminPaymentSearch adminPaymentSearch = new AdminPaymentSearch();
        Page<RicePaymentDTO> ricePaymentDTOS = ricePaymentService.getRicePayment(page, adminPaymentSearch, RicePaymentType.환전대기, RicePaymentType.환전승인);

        model.addAttribute("ricePaymentDTOS", ricePaymentDTOS.getContent());
        model.addAttribute("pageDTO", new PageDTO(ricePaymentDTOS));
        return "admin/gongyangmi-refund";
    }

    @GetMapping("inquiry")
    public String inquiry(Integer page, Model model) {
        page = page == null ? 0 : page - 1;
        AdminBoardSearch adminBoardSearch = new AdminBoardSearch();
        Page<InquiryDTO> inquiryDTOS = inquiryService.getInquiry(page, adminBoardSearch);

        model.addAttribute("inquiryDTOS", inquiryDTOS.getContent());
        model.addAttribute("pageDTO", new PageDTO(inquiryDTOS));
        return "admin/inquiry";
    }

    @GetMapping("notice")
    public String notice(Integer page, String searchType, String searchContent, Model model) {
        page = page == null ? 0 : page - 1;
        searchType = searchType == null ? "" : searchType;
        AdminNoticeSearch adminNoticeSearch = new AdminNoticeSearch();

        if(searchType.equals("제목")) {
            adminNoticeSearch.setNoticeTitle(searchContent);
        } else if(searchType.equals("내용")) {
            adminNoticeSearch.setNoticeContent(searchContent);
        }

        Page<NoticeDTO> noticeDTOS = noticeService.getNotice(page, adminNoticeSearch);

        model.addAttribute("noticeDTOS", noticeDTOS.getContent());
        model.addAttribute("pageDTO", new PageDTO(noticeDTOS));
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchContent", searchContent);
        return "admin/notice";
    }

    @PostMapping("notice-update")
    public RedirectView noticeUpdate(Long id, String noticeTitle, String noticeContent) {
        NoticeDTO noticeDTO = NoticeDTO.builder().id(id).noticeTitle(noticeTitle).noticeContent(noticeContent).build();
        noticeService.setNotice(noticeDTO);
        return new RedirectView("/admin/notice");
    }

    @GetMapping("payment")
    public String payment(Integer page, Model model) {
        page = page == null ? 0 : page - 1;
        AdminPaymentSearch adminPaymentSearch = new AdminPaymentSearch();
        Page<RicePaymentDTO> ricePaymentDTOS = ricePaymentService.getRicePayment(page, adminPaymentSearch, RicePaymentType.충전);

        model.addAttribute("ricePaymentDTOS", ricePaymentDTOS.getContent());
        model.addAttribute("pageDTO", new PageDTO(ricePaymentDTOS));
        return "admin/payment";
    }

    @GetMapping("review")
    public String review() {
        return "admin/review";
    }

    @GetMapping("sponsorship-request")
    public String sponsorshipRequest(Integer page, Model model) {
        page = page == null ? 0 : page - 1;
        AdminSupportRequestSearch adminSupportRequestSearch = new AdminSupportRequestSearch();
        Page<SupportRequestDTO> supportRequestDTOS = supportRequestService.getSupportRequest(page, adminSupportRequestSearch);

        log.info(supportRequestDTOS.getContent().toString());
        model.addAttribute("supportRequestDTOS", supportRequestDTOS.getContent());
        model.addAttribute("pageDTO", new PageDTO(supportRequestDTOS));
        return "admin/sponsorship-request";
    }

    @GetMapping("storage")
    public String storage() {
        return "admin/storage";
    }

    @GetMapping("user")
    public String user(Integer page, Model model) {
        page = page == null ? 0 : page - 1;
        AdminMemberSearch adminMemberSearch = new AdminMemberSearch();
        Page<MemberDTO> memberDTOS = memberService.getMembers(page, adminMemberSearch);

        model.addAttribute("memberDTOS", memberDTOS.getContent());
        model.addAttribute("pageDTO", new PageDTO(memberDTOS));
        return "admin/user";
    }

    @PostMapping("user")
    public RedirectView user(Integer page, Long id, String memberName, int memberRice, String memberAddress, String memberInterest, int memberVolunteerTime, Model model) {
        MemberDTO memberDTO = memberService.getMemberById(id);
        memberDTO.setMemberName(memberName);
        memberDTO.setMemberRice(memberRice);
        memberDTO.setMemberAddress(memberAddress);
        memberDTO.setMemberInterest(memberInterest);
        memberDTO.setMemberVolunteerTime(memberVolunteerTime);
        memberService.join(memberDTO, passwordEncoder);

        page = page == null ? 0 : page - 1;
        AdminMemberSearch adminMemberSearch = new AdminMemberSearch();
        Page<MemberDTO> memberDTOS = memberService.getMembers(page, adminMemberSearch);
        PageDTO pageDTO = new PageDTO(memberDTOS);

        model.addAttribute("memberDTOS", memberDTOS.getContent());
        model.addAttribute("pageDTO", pageDTO);
        return new RedirectView("/admin/user?page=" + pageDTO.getPageNum());
    }

    @GetMapping("volunteer")
    public String volunteer(Integer page, Model model) {
        page = page == null ? 0 : page - 1;
        AdminVolunteerSearch adminVolunteerSearch = new AdminVolunteerSearch();
        Page<VolunteerWorkDTO> volunteerWorkDTOS = volunteerWorkService.getVolunteerWork(page, adminVolunteerSearch);

        model.addAttribute("volunteerWorkDTOS", volunteerWorkDTOS.getContent());
        model.addAttribute("pageDTO", new PageDTO(volunteerWorkDTOS));
        return "admin/volunteer";
    }

    @GetMapping("volunteer-recruitment-board")
    public String volunteerRecruitmentBoard(Integer page, Model model) {
        page = page == null ? 0 : page - 1;
        AdminBoardSearch adminBoardSearch = new AdminBoardSearch();
        Page<FreeBoardDTO> freeBoardDTOS = freeBoardService.getFreeBoard(page, adminBoardSearch);

        model.addAttribute("freeBoardDTOS", freeBoardDTOS.getContent());
        model.addAttribute("pageDTO", new PageDTO(freeBoardDTOS));
        return "admin/volunteer-recruitment-board";
    }
}
