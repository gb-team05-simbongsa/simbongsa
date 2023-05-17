package com.app.simbongsa.controller;

import com.app.simbongsa.domain.*;
import com.app.simbongsa.entity.inquiry.Notice;
import com.app.simbongsa.search.admin.AdminNoticeSearch;
import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.domain.NoticeDTO;
import com.app.simbongsa.service.board.FreeBoardService;
import com.app.simbongsa.service.board.ReviewService;
import com.app.simbongsa.service.funding.FundingService;
import com.app.simbongsa.service.inquiry.AnswerService;
import com.app.simbongsa.service.inquiry.InquiryService;
import com.app.simbongsa.service.inquiry.NoticeService;
import com.app.simbongsa.service.member.MemberService;
import com.app.simbongsa.service.rice.RicePaymentService;
import com.app.simbongsa.service.support.SupportRequestService;
import com.app.simbongsa.service.support.SupportService;
import com.app.simbongsa.service.volunteer.VolunteerWorkService;
import com.app.simbongsa.type.MemberStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/admins/*")
@RequiredArgsConstructor
@Slf4j
public class AdminRestController {
    private final MemberService memberService;
    private final NoticeService noticeService;
    private final InquiryService inquiryService;
    private final AnswerService answerService;
    private final RicePaymentService ricePaymentService;
    private final SupportService supportService;
    private final VolunteerWorkService volunteerWorkService;
    private final FundingService fundingService;
    private final SupportRequestService supportRequestService;
    private final FreeBoardService freeBoardService;
    private final ReviewService reviewService;

    @PostMapping("notice-save")
    public void noticeSave(@RequestBody NoticeDTO noticeDTO) {
        log.info(noticeDTO.toString());
        noticeService.saveNotice(noticeDTO);
    }

    @PostMapping("member-details")
    public MemberDTO memberDetail(Long id) {
        return memberService.getMemberById(id);
    }

    @PostMapping("update-member-status")
    public void updateMemberStatus(Long[] ids) {
        List<Long> idList = new ArrayList<>();
        for (Long id: ids) {
            idList.add(id);
        }
        memberService.updateStatusByIds(idList);
    }

    @PostMapping("notice-details")
    public NoticeDTO noticeDetail(Long id) {
        return noticeService.getNoticeDetail(id);
    }

    @PostMapping("notices-delete")
    public void noticesDelete(Long[] ids) {
        List<Long> idList = new ArrayList<>();
        for (Long id : ids) {
            idList.add(id);
        }
        noticeService.deleteNotice(idList);
    }

    @PostMapping("inquiry-details")
    public InquiryDTO inquiryDetail(Long id) {
        InquiryDTO inquiryDetail = inquiryService.getInquiryDetail(id);
        return inquiryDetail;
    }

    @PostMapping("inquiries-delete")
    public void inquiriesDelete(Long[] ids) {
        List<Long> idList = new ArrayList<>();
        for (Long id : ids) {
            idList.add(id);
        }
        inquiryService.deleteInquiry(idList);
    }

    @PostMapping("fundings-delete")
    public void fundingsDelete(Long[] ids) {
        List<Long> idList = new ArrayList<>();
        for (Long id : ids) {
            idList.add(id);
        }
        fundingService.deleteFunding(idList);
    }

    @PostMapping("payments-delete")
    public void paymentsDelete(Long[] ids) {
        List<Long> idList = new ArrayList<>();
        for (Long id : ids) {
            idList.add(id);
        }
        ricePaymentService.deleteRicePaymentByIds(idList);
    }

    @PostMapping("rice-exchanges-delete")
    public void riceExchangesDelete(Long[] ids) {
        List<Long> idList = new ArrayList<>();
        for (Long id : ids) {
            idList.add(id);
        }
        ricePaymentService.deleteRicePaymentByIds(idList);
    }

    @PostMapping("rice-exchange-details")
    public RicePaymentDTO riceExchangeDetail(Long id) {
        return ricePaymentService.getRicePaymentDetail(id);
    }

    @PostMapping("support-list")
    public List<SupportDTO> supportList(Long id) {
        return supportService.getSupportList(id);
    }

    @PostMapping("support-requests-delete")
    public void supportRequestsDelete(Long[] ids) {
        List<Long> idList = new ArrayList<>();
        for (Long id : ids) {
            idList.add(id);
        }
        supportRequestService.deleteSupportRequest(idList);
    }

    @PostMapping("volunteers-delete")
    public void volunteersDelete(Long[] ids) {
        List<Long> idList = new ArrayList<>();
        for (Long id : ids) {
            idList.add(id);
        }
        volunteerWorkService.deleteVolunteerWorkByIds(idList);
    }

    @PostMapping("reviews-delete")
    public void reviewsDelete(Long[] ids) {
        List<Long> idList = new ArrayList<>();
        for (Long id : ids) {
            idList.add(id);
        }
        reviewService.deleteReviewByIds(idList);
    }
    
    @PostMapping("free-boards-delete")
    public void freeBoardsDelete(Long[] ids) {
        List<Long> idList = new ArrayList<>();
        for (Long id : ids) {
            idList.add(id);
        }
        freeBoardService.deleteFreeBoardByIds(idList);
    }
}
