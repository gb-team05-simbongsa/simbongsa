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
import com.app.simbongsa.type.FileRepresentationalType;
import com.app.simbongsa.type.MemberStatus;
import com.app.simbongsa.type.RequestType;
import com.app.simbongsa.type.RicePaymentType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.io.File;
import java.io.IOException;
import java.util.*;

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
        return inquiryService.getInquiryDetail(id);
    }

    @PostMapping("inquiries-delete")
    public void inquiriesDelete(Long[] ids) {
        List<Long> idList = new ArrayList<>();
        for (Long id : ids) {
            idList.add(id);
        }
        inquiryService.deleteInquiry(idList);
    }

    @PostMapping("answer-registration")
    public void answerRegistration(String answerTitle, String answerContent, Long inquiryId) {
        AnswerDTO answerDTO = new AnswerDTO();
        answerDTO.setAnswerTitle(answerTitle);
        answerDTO.setAnswerContent(answerContent);
        answerDTO.setInquiryDTO(inquiryService.getInquiryDetail(inquiryId));

        answerService.saveAnswer(answerDTO);
        inquiryService.updateStatusById(answerDTO.getInquiryDTO().getId());
    }

    @PostMapping("funding-details")
    public FundingDTO fundingDetail(Long id) {
        return fundingService.getFundingDetail(id);
    }

    @PostMapping("fundings-delete")
    public void fundingsDelete(Long[] ids) {
        List<Long> idList = new ArrayList<>();
        for (Long id : ids) {
            idList.add(id);
        }
        fundingService.deleteFunding(idList);
    }

    @PostMapping("update-fundings-status")
    public void updateFundingsStatus(Long[] ids, String status) {
        List<Long> idList = new ArrayList<>();
        for (Long id : ids) {
            idList.add(id);
        }

        RequestType requestType = status.equals("승인") ? RequestType.승인 : RequestType.반려;
        fundingService.updateFundingStatus(idList, requestType);
    }

    @PostMapping("payments-details")
    public RicePaymentDTO paymentDetail(Long id) {
        return ricePaymentService.getRicePaymentDetail(id);
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

    @PostMapping("update-rice-requests-status")
    public void updateRiceRequestsStatus(Long[] ids, String status) {
        List<Long> idList = new ArrayList<>();
        for (Long id : ids) {
            idList.add(id);
        }

        ricePaymentService.updatePaymentStatusToAccessByIds(idList);
    }

    @PostMapping("support-list")
    public Page<SupportDTO> supportList(Long id, Integer page) {
        return supportService.getAllSupportAttendWithMember_QueryDSL(page, id);
    }

    @PostMapping("update-requests-status")
    public void updateRequestsStatus(Long[] ids, String status) {
        List<Long> idList = new ArrayList<>();
        for (Long id : ids) {
            idList.add(id);
        }

        RequestType requestType = status.equals("승인") ? RequestType.승인 : RequestType.반려;
        supportRequestService.updateWaitToAccessOrDenied(idList, requestType);
    }

    @PostMapping("support-requests-delete")
    public void supportRequestsDelete(Long[] ids) {
        List<Long> idList = new ArrayList<>();
        for (Long id : ids) {
            idList.add(id);
        }
        supportRequestService.deleteSupportRequest(idList);
    }

    @PostMapping("volunteer-works-register")
    public void volunteerWorkRegister(@RequestBody VolunteerWorkDTO volunteerWorkDTO) {
        volunteerWorkService.saveVolunteerWork(volunteerWorkDTO);
    }

    @PostMapping("volunteers-delete")
    public void volunteersDelete(Long[] ids) {
        List<Long> idList = new ArrayList<>();
        for (Long id : ids) {
            idList.add(id);
        }
        volunteerWorkService.deleteVolunteerWorkByIds(idList);
    }

    @PostMapping("volunteer-work-details")
    public VolunteerWorkDTO volunteerDetails(Long id) {
        return volunteerWorkService.getVolunteerWorkDetail(id);
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
