package com.app.simbongsa.controller;

import com.app.simbongsa.domain.InquiryDTO;
import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.domain.NoticeDTO;
import com.app.simbongsa.domain.RicePaymentDTO;
import com.app.simbongsa.entity.inquiry.Inquiry;
import com.app.simbongsa.service.inquiry.InquiryService;
import com.app.simbongsa.service.member.MemberService;
import com.app.simbongsa.service.rice.RicePaymentService;
import com.app.simbongsa.type.RicePaymentType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/mypages/*")
@RequiredArgsConstructor
@Slf4j
public class MypageRestController {
    private final InquiryService inquiryService;
    private final RicePaymentService ricePaymentService;
    private final MemberService memberService;

    @PostMapping("inquiry-details")
    public InquiryDTO inquiryDetail(Long id) {
        return inquiryService.getInquiryDetail(id);
    }

    @PostMapping("inquiry-delete")
    public void deleteByInquiry(Long id) {
        inquiryService.deleteByInquiryId(id);
    }

//    @PostMapping("rices-charge")
//    public void riceCharge(Integer ricePaymentUsed, HttpSession session) {
//        MemberDTO memberDTO = (MemberDTO)session.getAttribute("member");
//
//        ricePaymentService.insertRicePayment(ricePaymentUsed, memberDTO);
////        memberService.insertRicePayment(ricePaymentDTO);
//    }

}
