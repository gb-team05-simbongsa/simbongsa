package com.app.simbongsa.controller;

import com.app.simbongsa.domain.PageDTO;
import com.app.simbongsa.domain.SupportRequestDTO;
import com.app.simbongsa.repository.support.SupportRequestRepository;
import com.app.simbongsa.service.support.SupportRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/support/*")
@RequiredArgsConstructor
@Slf4j
public class SupportController {
    private final SupportRequestService supportRequestService;

    @GetMapping("support-detail")
    public String supportDetail(){
        return "support/support-detail";
    }

    @GetMapping("support-list")
    public String supportList(Integer page, Model model, String keyword){
        log.info(keyword);
        if(keyword == null){
            keyword = "";
        }
        page = page == null ? 0 : page -1;

        Page<SupportRequestDTO> supportRequestDTOs = supportRequestService.getSupportRequestAllWithPaging(page, keyword);

        model.addAttribute("supportRequestDTOs", supportRequestDTOs);
        model.addAttribute("pageDTO", new PageDTO(supportRequestDTOs));
        return "support/support-list";
    }

    @GetMapping("support-success")
    public String supportSuccess(){

        return "support/support-success";
    }
    @GetMapping("support-write")
    public String supportWrite(){
        return "support/support-write";
    }
}
