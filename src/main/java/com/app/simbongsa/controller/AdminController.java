package com.app.simbongsa.controller;

import com.app.simbongsa.domain.*;
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
import com.app.simbongsa.type.RequestType;
import com.app.simbongsa.type.RicePaymentType;
import com.app.simbongsa.type.UserRankType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityManager;
import java.text.DecimalFormat;
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
    public String activityReviewBoard(Integer page, String searchType, String searchContent, Model model) {
        page = page == null ? 0 : page - 1;
        searchType = searchType == null ? "" : searchType;
        searchContent = searchContent == null ? "" : searchContent;
        AdminBoardSearch adminBoardSearch = new AdminBoardSearch();

        if(searchType.equals("제목")) {
            adminBoardSearch.setBoardTitle(searchContent);
        } else if(searchType.equals("이메일")) {
            adminBoardSearch.setMemberEmail(searchContent);
        }

        Page<ReviewDTO> reviewDTOS = reviewService.getReview(page, adminBoardSearch);

        model.addAttribute("reviewDTOS", reviewDTOS.getContent());
        model.addAttribute("pageDTO", new PageDTO(reviewDTOS));
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchContent", searchContent);
        return "admin/activity-review-board";
    }

    @GetMapping("funding")
    public String funding(Integer page, String searchType, String searchContent, Model model) {
        page = page == null ? 0 : page - 1;
        searchType = searchType == null ? "" : searchType;
        searchContent = searchContent == null ? "" : searchContent;
        AdminFundingSearch adminFundingSearch = new AdminFundingSearch();

        if(searchType.equals("제목")) {
            adminFundingSearch.setFundingTitle(searchContent);
        } else if(searchType.equals("창작자 이름")) {
            adminFundingSearch.setCreatorNickName(searchContent);
        }

        Page<FundingDTO> fundingDTOS = fundingService.getFunding(page, adminFundingSearch);

        List<Long> counts = fundingService.countAcceptAndWait();

        model.addAttribute("fundingDTOS", fundingDTOS.getContent());
        model.addAttribute("pageDTO", new PageDTO(fundingDTOS));
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchContent", searchContent);
        model.addAttribute("counts", counts);
        return "admin/funding";
    }

    @GetMapping("gongyangmi-refund")
    public String gongyangmiRefund(Integer page, String searchType, String searchContent, Model model) {
        page = page == null ? 0 : page - 1;
        searchType = searchType == null ? "" : searchType;
        searchContent = searchContent == null ? "" : searchContent;
        AdminPaymentSearch adminPaymentSearch = new AdminPaymentSearch();

        if(searchType.equals("요청 공양미(미만)")) {
            adminPaymentSearch.setRicePaymentUsed(Integer.parseInt(searchContent));
        } else if(searchType.equals("이메일")) {
            adminPaymentSearch.setMemberEmail(searchContent);
        }

        Page<RicePaymentDTO> ricePaymentDTOS = ricePaymentService.getRicePayment(page, adminPaymentSearch, RicePaymentType.환전대기, RicePaymentType.환전승인);

        List<Long> counts = ricePaymentService.countStatusWaitAccessDenied();

        model.addAttribute("ricePaymentDTOS", ricePaymentDTOS.getContent());
        model.addAttribute("pageDTO", new PageDTO(ricePaymentDTOS));
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchContent", searchContent);
        model.addAttribute("counts", counts);
        return "admin/gongyangmi-refund";
    }

    @GetMapping("inquiry")
    public String inquiry(Integer page, String searchType, String searchContent, Model model) {
        page = page == null ? 0 : page - 1;
        searchType = searchType == null ? "" : searchType;
        searchContent = searchContent == null ? "" : searchContent;
        AdminBoardSearch adminBoardSearch = new AdminBoardSearch();

        if(searchType.equals("제목")) {
            adminBoardSearch.setBoardTitle(searchContent);
        } else if(searchType.equals("이메일")) {
            adminBoardSearch.setMemberEmail(searchContent);
        }

        Page<InquiryDTO> inquiryDTOS = inquiryService.getInquiry(page, adminBoardSearch);

        List<Long> counts = inquiryService.countStatusWaitAndComplete();

        model.addAttribute("inquiryDTOS", inquiryDTOS.getContent());
        model.addAttribute("pageDTO", new PageDTO(inquiryDTOS));
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchContent", searchContent);
        model.addAttribute("counts", counts);
        return "admin/inquiry";
    }

    @GetMapping("notice")
    public String notice(Integer page, String searchType, String searchContent, Model model) {
        page = page == null ? 0 : page - 1;
        searchType = searchType == null ? "" : searchType;
        searchContent = searchContent == null ? "" : searchContent;
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
    public String payment(Integer page, String searchType, String searchContent, Model model) {
        page = page == null ? 0 : page - 1;
        searchType = searchType == null ? "" : searchType;
        searchContent = searchContent == null ? "" : searchContent;
        AdminPaymentSearch adminPaymentSearch = new AdminPaymentSearch();

        if(searchType.equals("공양미 충전 수")) {
            adminPaymentSearch.setRicePaymentUsed(Integer.parseInt(searchContent));
        } else if(searchType.equals("이메일")) {
            adminPaymentSearch.setMemberEmail(searchContent);
        }

        Page<RicePaymentDTO> ricePaymentDTOS = ricePaymentService.getRicePayment(page, adminPaymentSearch, RicePaymentType.충전);

        List<Long> counts = ricePaymentService.getPaymentPriceAndPaymentCount();

        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        model.addAttribute("ricePaymentDTOS", ricePaymentDTOS.getContent());
        model.addAttribute("pageDTO", new PageDTO(ricePaymentDTOS));
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchContent", searchContent);
        model.addAttribute("payment", counts.get(0));
        model.addAttribute("price", decimalFormat.format(counts.get(1)));
        return "admin/payment";
    }

    @GetMapping("review")
    public String review() {
        return "admin/review";
    }

    @GetMapping("sponsorship-request")
    public String sponsorshipRequest(Integer page, String searchType, String searchContent, Model model) {
        page = page == null ? 0 : page - 1;
        searchType = searchType == null ? "" : searchType;
        searchContent = searchContent == null ? "" : searchContent;
        AdminSupportRequestSearch adminSupportRequestSearch = new AdminSupportRequestSearch();

        if(searchType.equals("승인/대기")) {
            adminSupportRequestSearch.setRequestType(RequestType.valueOf(searchContent));
        } else if(searchType.equals("이메일")) {
            adminSupportRequestSearch.setMemberEmail(searchContent);
        }

        Page<SupportRequestDTO> supportRequestDTOS = supportRequestService.getSupportRequest(page, adminSupportRequestSearch);

        log.info(supportRequestDTOS.getContent().toString());
        List<Long> counts = supportRequestService.countStatusWaitAccessDenied();

        model.addAttribute("supportRequestDTOS", supportRequestDTOS.getContent());
        model.addAttribute("pageDTO", new PageDTO(supportRequestDTOS));
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchContent", searchContent);
        model.addAttribute("counts", counts);
        return "admin/sponsorship-request";
    }

    @GetMapping("storage")
    public String storage() {
        return "admin/storage";
    }

    @GetMapping("user")
    public String user(Integer page, String searchType, String searchContent, Model model) {
        page = page == null ? 0 : page - 1;
        searchType = searchType == null ? "" : searchType;
        searchContent = searchContent == null ? "" : searchContent;
        AdminMemberSearch adminMemberSearch = new AdminMemberSearch();

        if(searchType.equals("이메일")) {
            adminMemberSearch.setMemberEmail(searchContent);
        } else if(searchType.equals("주소")) {
            adminMemberSearch.setMemberAddress(searchContent);
        }

        Page<MemberDTO> memberDTOS = memberService.getMembers(page, adminMemberSearch);

        model.addAttribute("memberDTOS", memberDTOS.getContent());
        model.addAttribute("pageDTO", new PageDTO(memberDTOS));
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchContent", searchContent);
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

        UserRankType userRankType = null;
        if(memberVolunteerTime >= 0 && memberVolunteerTime < 10) {
            userRankType = UserRankType.동냥1티어;
        } else if(memberVolunteerTime >= 10 && memberVolunteerTime < 30) {
            userRankType = UserRankType.동냥2티어;
        } else if(memberVolunteerTime >= 30 && memberVolunteerTime < 50) {
            userRankType = UserRankType.동냥3티어;
        } else if(memberVolunteerTime >= 50 && memberVolunteerTime < 80) {
            userRankType = UserRankType.은냥1티어;
        } else if(memberVolunteerTime >= 80 && memberVolunteerTime < 100) {
            userRankType = UserRankType.은냥2티어;
        } else if(memberVolunteerTime >= 100 && memberVolunteerTime < 150) {
            userRankType = UserRankType.은냥3티어;
        } else if(memberVolunteerTime >= 150 && memberVolunteerTime < 200) {
            userRankType = UserRankType.금냥1티어;
        } else if(memberVolunteerTime >= 200 && memberVolunteerTime < 300) {
            userRankType = UserRankType.금냥2티어;
        } else if(memberVolunteerTime >= 300) {
            userRankType = UserRankType.금냥3티어;
        }

        memberDTO.setMemberRank(userRankType);
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
    public String volunteer(Integer page, String searchType, String searchContent, Model model) {
        page = page == null ? 0 : page - 1;
        searchType = searchType == null ? "" : searchType;
        searchContent = searchContent == null ? "" : searchContent;
        AdminVolunteerSearch adminVolunteerSearch = new AdminVolunteerSearch();

        if(searchType.equals("봉사위치")) {
            adminVolunteerSearch.setVolunteerWorkPlace(searchContent);
        } else if(searchType.equals("등록기관")) {
            adminVolunteerSearch.setVolunteerWorkRegisterAgency(searchContent);
        }

        Page<VolunteerWorkDTO> volunteerWorkDTOS = volunteerWorkService.getVolunteerWork(page, adminVolunteerSearch);

        model.addAttribute("volunteerWorkDTOS", volunteerWorkDTOS.getContent());
        model.addAttribute("pageDTO", new PageDTO(volunteerWorkDTOS));
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchContent", searchContent);
        return "admin/volunteer";
    }

    @GetMapping("volunteer-recruitment-board")
    public String volunteerRecruitmentBoard(Integer page, String searchType, String searchContent, Model model) {
        page = page == null ? 0 : page - 1;
        searchType = searchType == null ? "" : searchType;
        searchContent = searchContent == null ? "" : searchContent;
        AdminBoardSearch adminBoardSearch = new AdminBoardSearch();

        if(searchType.equals("제목")) {
            adminBoardSearch.setBoardTitle(searchContent);
        } else if(searchType.equals("이메일")) {
            adminBoardSearch.setMemberEmail(searchContent);
        }

        Page<FreeBoardDTO> freeBoardDTOS = freeBoardService.getFreeBoard(page, adminBoardSearch);

        model.addAttribute("freeBoardDTOS", freeBoardDTOS.getContent());
        model.addAttribute("pageDTO", new PageDTO(freeBoardDTOS));
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchContent", searchContent);
        return "admin/volunteer-recruitment-board";
    }
}
