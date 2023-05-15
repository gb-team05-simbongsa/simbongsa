package com.app.simbongsa.controller;

import com.app.simbongsa.domain.FreeBoardDTO;
import com.app.simbongsa.entity.board.FreeBoard;
import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.service.board.FreeBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.awt.print.Pageable;

@Controller
@RequestMapping("/community/*")
@RequiredArgsConstructor
@Slf4j
public class CommunityController {
    private final FreeBoardService freeBoardService;

    @GetMapping("free-board")
    public void goToFreeBoard(@AuthenticationPrincipal UserDetail userDetail, Model model){
        model.addAttribute("userDerail", userDetail);
    }



    @GetMapping("free-create")
    public void goToFreeCreate(FreeBoardDTO freeBoardDTO) {}

    @PostMapping("free-create")
    public RedirectView freeCreate(@ModelAttribute("freeBoardDTO") FreeBoardDTO freeBoardDTO, @AuthenticationPrincipal UserDetail userDetail){

        Long memberId = userDetail.getId();
        freeBoardService.register(freeBoardDTO, memberId);
        return new RedirectView("community/free-create");
    }

    @GetMapping("board-modify")
    public String freeModify() {return "community/board-modify";}

    @GetMapping("free-detail")
    public String freeDetail() {return "community/free-detail";}

    @GetMapping("review-board")
    public String reviewBoard() {return "community/review-board";}

    @GetMapping("review-create")
    public String reviewCreate() {return "community/review-create";}

    @GetMapping("review-detail")
    public String reviewDerail() {return "community/review-detail";}
}
