package com.app.simbongsa.controller;

import com.app.simbongsa.domain.FreeBoardDTO;
import com.app.simbongsa.domain.FundingDTO;
import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.domain.VolunteerWorkDTO;
import com.app.simbongsa.service.board.FreeBoardService;
import com.app.simbongsa.service.funding.FundingService;
import com.app.simbongsa.service.member.MemberService;
import com.app.simbongsa.service.volunteer.VolunteerWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/main/*")
@RequiredArgsConstructor
public class MainController {
    private final VolunteerWorkService volunteerWorkService;
    private final MemberService memberService;
    private final FundingService fundingService;
    private final FreeBoardService freeBoardService;

    @GetMapping("")
    public String main(Model model) {
        List<VolunteerWorkDTO> volunteerList = volunteerWorkService.getVolunteerList();
        List<MemberDTO> memberRankList = memberService.getMemberRankingList();
        List<FundingDTO> fundingListOrderByPopularList = fundingService.getAllPopularFundingList();
        List<FreeBoardDTO> freeBoardList = freeBoardService.findAllWithPopularFreeBoard();

        model.addAttribute("volunteerList", volunteerList);
        model.addAttribute("memberRankList", memberRankList);
        model.addAttribute("fundingListOrderByPopularList",fundingListOrderByPopularList);
        model.addAttribute("freeBoardList", freeBoardList);
        return "main/main";
    }
}
