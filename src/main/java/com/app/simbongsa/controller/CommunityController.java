package com.app.simbongsa.controller;

import com.app.simbongsa.domain.FreeBoardDTO;
import com.app.simbongsa.domain.FreeBoardReplyDTO;
import com.app.simbongsa.domain.ReplyDTO;
import com.app.simbongsa.domain.ReviewReplyDTO;
import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.service.board.FreeBoardService;
import com.app.simbongsa.service.board.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/community/*")
@RequiredArgsConstructor
@Slf4j
public class CommunityController {
    private final FreeBoardService freeBoardService;
    private final ReviewService reviewService;

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

    @GetMapping("free-detail/{boardId}")
    public String goToFreeDetail(Model model, @PathVariable("boardId") Long boardId, @AuthenticationPrincipal UserDetail userDetail) {
        FreeBoardDTO freeBoardDTO = freeBoardService.getFreeBoard(boardId);

        model.addAttribute("freeBoardDTO", freeBoardDTO);
        model.addAttribute("userDetail", userDetail);
        return "community/free-detail";
    }

    @GetMapping("review-board")
    public String reviewBoard() {return "community/review-board";}

    @GetMapping("review-create")
    public String reviewCreate() {return "community/review-create";}

    @GetMapping("review-detail")
    public String reviewDerail() {return "community/review-detail";}

    /* 자유게시판 댓글*/
    @PostMapping("save")
    public void saveReply(@RequestParam FreeBoardReplyDTO freeBoardReplyDTO){
        freeBoardService.registerReply(freeBoardReplyDTO);
    }

    @DeleteMapping("delete")
    public void deleteFreeReply(@RequestParam("replyId") Long replyId){freeBoardService.deleteReply(replyId);}

    @GetMapping("list")
    public Slice<ReplyDTO> getFreeList(@RequestParam("boardId") Long freeBoardId, @RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageable = PageRequest.of(page, 8);
        return freeBoardService.getReplyList(freeBoardId, pageable);
    }


    /*활동후기 댓글*/
    @PostMapping("save")
    public void saveReply(@RequestParam ReviewReplyDTO reviewReplyDTO){
        reviewService.registerReply(reviewReplyDTO);
    }

    @DeleteMapping("delete")
    public void deleteReviewReply(@RequestParam("replyId") Long replyId){reviewService.deleteReply(replyId);}

    @GetMapping("list")
    public Slice<ReplyDTO> getReviewList(@RequestParam("boardId") Long reviewId, @RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageable = PageRequest.of(page, 8);
        return reviewService.getReplyList(reviewId, pageable);
    }
}
