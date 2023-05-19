package com.app.simbongsa.controller;

import com.app.simbongsa.domain.*;
import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.service.board.FreeBoardService;
import com.app.simbongsa.service.funding.FundingService;
import com.app.simbongsa.service.member.MemberService;
import com.app.simbongsa.service.volunteer.VolunteerWorkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/main/*")
@RequiredArgsConstructor
@Slf4j
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
        List<FreeBoardDTO> freeBoardList = freeBoardService.getAllWithPopularFreeBoard();



        // 인기 펀딩
        List<FileDTO> fileDTO = fundingService.getAllPopularFundingList()
                .stream()
                .flatMap(funding -> funding.getFileDTOs().stream())
                .collect(Collectors.toList());

        // 봉사 파일
        List<FileDTO> volunteerFileDTO = volunteerWorkService.getVolunteerList()
                .stream()
                .flatMap(volunteer -> volunteer.getFileDTOs().stream())
                .collect(Collectors.toList());
        volunteerFileDTO.stream().map(FileDTO::toString).forEach(log::info);

        // 게시판 파일
        List<FileDTO> freeBoardFileDTO = Optional.ofNullable(freeBoardService.getAllWithFile())
                .orElse(Collections.emptyList())
                .stream()
                .flatMap(boardFile -> boardFile.getFileDTOS().stream())
                .collect(Collectors.toList());
        freeBoardFileDTO.stream().map(FileDTO::toString).forEach(log::info);

        model.addAttribute("fileDTO", fileDTO);
        model.addAttribute("volunteerFileDTO", volunteerFileDTO);
        model.addAttribute("volunteerList", volunteerList);
        model.addAttribute("memberRankList", memberRankList);

        model.addAttribute("fundingListOrderByPopularList",fundingListOrderByPopularList);

        model.addAttribute("freeBoardList", freeBoardList);
        model.addAttribute("freeBoardFileDTO", freeBoardFileDTO);
        return "main/main";

    }

    @GetMapping("info")
     public String serviceInfo(){
        return "service-info/service-info";
    }

}
