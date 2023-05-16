package com.app.simbongsa.controller;

import com.app.simbongsa.domain.*;
import com.app.simbongsa.entity.inquiry.Notice;
import com.app.simbongsa.search.admin.AdminNoticeSearch;
import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.domain.NoticeDTO;
import com.app.simbongsa.service.inquiry.AnswerService;
import com.app.simbongsa.service.inquiry.InquiryService;
import com.app.simbongsa.service.inquiry.NoticeService;
import com.app.simbongsa.service.member.MemberService;
import com.app.simbongsa.service.rice.RicePaymentService;
import com.app.simbongsa.service.support.SupportService;
import com.app.simbongsa.service.volunteer.VolunteerWorkService;
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

    @PostMapping("notice-save")
    public void noticeSave(@RequestBody NoticeDTO noticeDTO) {
        log.info(noticeDTO.toString());
        noticeService.saveNotice(noticeDTO);
    }

    @PostMapping("member-details")
    public MemberDTO memberDetail(Long id) {
        return memberService.getMemberById(id);
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

    @PostMapping("rice-exchange-details")
    public RicePaymentDTO riceExchangeDetail(Long id) {
        return ricePaymentService.getRicePaymentDetail(id);
    }

    @PostMapping("support-list")
    public List<SupportDTO> supportList(Long id) {
        return supportService.getSupportList(id);
    }

    @PostMapping("volunteers-delete")
    public void volunteersDelete(Long[] ids) {
        List<Long> idList = new ArrayList<>();
        for (Long id : ids) {
            idList.add(id);
        }
        volunteerWorkService.deleteVolunteerWorkByIds(idList);
    }
}
