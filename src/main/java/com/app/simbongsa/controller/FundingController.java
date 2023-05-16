package com.app.simbongsa.controller;

import com.app.simbongsa.domain.FundingDTO;
import com.app.simbongsa.domain.FundingItemDTO;
import com.app.simbongsa.service.funding.FundingItemService;
import com.app.simbongsa.service.funding.FundingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final FundingItemService fundingItemService;


    @GetMapping("funding-creater-info")
    public String fundingCreate() {return "funding/funding-creater-info.html";}

    @GetMapping("funding-detail")
    public String fundingDetail() {return "funding/funding-detail.html";}


//    @GetMapping("funding-detail{fundingId}")
//    public String fundingDetail(@PathVariable Long fundingId, Model model) {
//        model.addAttribute("funding", fundingService.getFundingDetail(fundingId));
//
//        return "/funding/funding-detail";}


    @GetMapping("funding-gift")
    public String fundingGift() {return "funding/funding-gift.html";}

    @GetMapping("funding-initial-info")
    public String fundingInitial() {return "funding/funding-initial-info.html";}

    @GetMapping("funding-item")
    public String fundingItemForm() {return "funding/funding-item.html";}

//    @PostMapping("funding-item")
//    public String fundingItem(FundingItemDTO fundingItemDTO){
//        fundingItemService.ItemSave(fundingItemDTO);
//        return
//    }

//    @GetMapping("funding-list")
//    public String fundinglist() {return "funding/funding-list.html";}

    @GetMapping("funding-list")
    @ResponseBody
    public Slice<FundingDTO> getFundingList(@RequestParam(defaultValue = "0", name = "page") int page, Model model) {
    PageRequest pageRequest = PageRequest.of(page, 12);
    return fundingService.getFundingList(pageRequest);

    }

    @GetMapping("funding-payment")
    public String fundingPayment() {return "funding/funding-payment.html";}

    @GetMapping("funding-plan")
    public String fundingPlan() {return "funding/funding-plan.html";}

    @GetMapping("funding-plan-main")
    public String fundingPlanMain() {return "funding/funding-plan-main.html";}

    @GetMapping("funding-project-plan")
    public String fundingProjectPlan() {return "funding/funding-project-plan.html";}

    @GetMapping("funding-result")
    public String fundingResult() {return "funding/funding-result.html";}

    @GetMapping("funding-start")
    public String fundingStart() {return "funding/funding-start.html";}

    @GetMapping("funding-topContent")
    public String fundingTopContent() {return "funding/funding-topContent.html";}
}
