package com.app.simbongsa.controller;

import com.app.simbongsa.entity.board.FreeBoard;
import com.app.simbongsa.service.board.FreeBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.print.Pageable;

@Controller
@RequestMapping("/community/*")
@RequiredArgsConstructor
@Slf4j
public class CommunityController {
    private final FreeBoardService freeBoardService;

    @GetMapping("free-board")
    public String freeBoard() {return "community/free-board";}

//    @GetMapping("/free-board")
//    public String freeBoard(Model model, Pageable pageable) {
//        Slice<FreeBoard> newFreeBoards = freeBoardService.getAllNewFreeBoards(pageable);
//        model.addAttribute("freeBoards", newFreeBoards);
//        return "community/free-board";
//    }


    @GetMapping("free-create")
    public String freeCreate() {return "community/free-create";}

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
