package com.app.simbongsa.controller;

import com.app.simbongsa.domain.FundingDTO;
import com.app.simbongsa.domain.FundingItemDTO;
import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.entity.funding.FundingCreator;
import com.app.simbongsa.service.funding.FundingItemService;
import com.app.simbongsa.service.funding.FundingService;
import com.app.simbongsa.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.Member;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/funding/*")
@RequiredArgsConstructor
@Slf4j
public class FundingController {
    private final FundingService fundingService;
    private  final MemberService memberService;

    private final FundingItemService fundingItemService;


    @GetMapping("funding-creator-info")
    public String fundingCreateForm() {return "funding/funding-creater-info";}

    @PostMapping("funding-creator-info")
    public String fundingCreate(Model model, FundingDTO fundingDTO) {

//        fundingDTO.getFundingCreator().setFundingCreatorNickname();
//        fundingDTO.getFundingCreator().setFundingCreatorIntroduce();
//        fundingService.fundingRegister(fundingDTO);

        return "funding/funding-creater-info";
    }

    @GetMapping("funding-detail")
    public String fundingDetail() {return "funding/funding-detail";}


//    @GetMapping("funding-detail{fundingId}")
//    public String fundingDetail(@PathVariable Long fundingId, Model model) {
//        model.addAttribute("funding", fundingService.getFundingDetail(fundingId));
//
//        return "/funding/funding-detail";}


    @GetMapping("funding-gift")
    public String fundingGift() {return "funding/funding-gift.html";}

    @GetMapping("funding-initial-info")
    public String fundingInitialForm() {return "funding/funding-initial-info.html";}

    @PostMapping("funding-initial-info")
    @ResponseBody
    public String fundingInitial() {
        return "funding/funding-initial-info";}

    @GetMapping("funding-item")
    public String fundingItemForm() {return "funding/funding-item.html";}

//    @PostMapping("funding-item")
//    public String fundingItem(FundingItemDTO fundingItemDTO){
//        fundingItemService.ItemSave(fundingItemDTO);
//        return
//    }

//    @GetMapping("funding-list")
//    public String fundinglist() {return "funding/funding-list.html";}

    @GetMapping("funding")
    public String goToFundingList(Model model, Long fundingId){
            model.addAttribute("count", fundingService.getFundingCount(fundingId));

        return "/funding/funding-list";
    }

    @GetMapping("funding-list")
    @ResponseBody
    public Slice<FundingDTO> getFundingList(@RequestParam(defaultValue = "0", name = "page") int page) {
    PageRequest pageRequest = PageRequest.of(page, 12);

    return fundingService.getFundingList(pageRequest);

    }

//    @GetMapping("funding-payment")
//    public String fundingPayment() {return "funding/funding-payment.html";}

    @GetMapping("funding-payment")
    public String fundingPayment(Model model, Long memberId, Long fundingId) {
        MemberDTO memberDTO = memberService.getMemberById(memberId);
        FundingDTO fundingDTO = fundingService.getFundingDetail(fundingId);

        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("fundingDTO", fundingDTO);


        return "funding/funding-payment";}

    @GetMapping("funding-plan")
    public String fundingPlan() {return "funding/funding-plan.html";}

    @GetMapping("funding-plan-main")
    public String fundingPlanMain() {return "funding/funding-plan-main.html";}

    @GetMapping("funding-project-plan")
    public String fundingProjectPlanForm() {return "funding/funding-project-plan";}

    @PostMapping("funding-project-plan")
    public String fundingProjectPlan(FundingDTO fundingDTO, Long fundingId) {

        String fundingCreatorName = fundingDTO.getFundingCreator().getFundingCreatorNickname();
        String fundingIntroduce = fundingDTO.getFundingCreator().getFundingCreatorIntroduce();

        fundingDTO.getFundingCreator().setFundingCreatorNickname(fundingCreatorName);
        fundingDTO.getFundingCreator().setFundingCreatorIntroduce(fundingIntroduce);

        fundingService.fundingRegister(fundingDTO, fundingId);
        return "funding/funding-project-plan";}




        @GetMapping("funding-result")
    public String fundingResult() {return "funding/funding-result.html";}

    @GetMapping("funding-start")
    public String fundingStart() {return "funding/funding-start.html";}

    @GetMapping("funding-topContent")
    public String fundingTopContent() {return "funding/funding-topContent.html";}
}
