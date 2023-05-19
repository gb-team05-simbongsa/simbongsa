package com.app.simbongsa.controller;

import com.app.simbongsa.domain.SupportDTO;
import com.app.simbongsa.service.support.SupportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supports/*")
@RequiredArgsConstructor
@Slf4j
public class SupportRestController {
    private final SupportService supportService;
    @ResponseBody
    @GetMapping("attend-list") //멤버 목록
    public Page<SupportDTO> getAttendList(@RequestParam(value = "page") int page, Long supportRequestId){

        PageRequest pageRequest = PageRequest.of(page, 5);
        //        후원 참여 수 페이징 REST로 처리할 예정
        Page<SupportDTO> attendList = supportService.getAllSupportAttendWithMember_QueryDSL(pageRequest, 380L);
        attendList.stream().map(SupportDTO::toString).forEach(log::info);
        log.info("들어오나?");
//        page = page == null ? 0 : page -1;
        return attendList;

    }

}

