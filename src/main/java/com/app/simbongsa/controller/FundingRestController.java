package com.app.simbongsa.controller;

import com.app.simbongsa.domain.FundingDTO;
import com.app.simbongsa.domain.VolunteerWorkDTO;
import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.service.funding.FundingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/funding/*")
@RequiredArgsConstructor
@Slf4j
public class FundingRestController {

    private final FundingService fundingService;
//
//    @PostMapping("funding-initial-info")
//    public void volunteerWorkRegister(@RequestBody FundingDTO fundingDTO) {
//        log.info(fundingDTO.toString());
//        fundingService.fundingRegister(fundingDTO);
//    }

}
