package com.app.simbongsa.controller;

import com.app.simbongsa.domain.*;
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
    @GetMapping("free-board-content")
    @ResponseBody
    public Slice<FreeBoardDTO> getFreeNewList(@RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageRequest = PageRequest.of(page,8);
        return freeBoardService.getNewList(pageRequest);
    }

    @GetMapping("free-board-content-likes")
    @ResponseBody
    public Slice<FreeBoardDTO> getFreeLikesList(@RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageRequest = PageRequest.of(page,8);
        return freeBoardService.getLikesList(pageRequest);
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
    public void goToReviewBoard(@AuthenticationPrincipal UserDetail userDetail, Model model){
        model.addAttribute("userDerail", userDetail);
    }

    @GetMapping("review-board-content")
    @ResponseBody
    public Slice<ReviewDTO> getReviewNewList(@RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageRequest = PageRequest.of(page,8);
        return reviewService.getNewReviewList(pageRequest);
    }

    @GetMapping("review-board-content-likes")
    @ResponseBody
    public Slice<ReviewDTO> getReviewLikesList(@RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageRequest = PageRequest.of(page,8);
        return reviewService.getLikesReviewList(pageRequest);
    }

    @GetMapping("review-create")
    public void goToReviewCreate(ReviewDTO reviewDTO) {}

    @PostMapping("review-create")
    public RedirectView reviewCreate(@ModelAttribute("reviewDTO") ReviewDTO reviewDTO, @AuthenticationPrincipal UserDetail userDetail){

        Long memberId = userDetail.getId();
        reviewService.register(reviewDTO, memberId);
        return new RedirectView("community/review-create");
    }

    @GetMapping("review-detail/{boardId}")
    public String goToReviewDerail(Model model, @PathVariable("boardId") Long boardId, @AuthenticationPrincipal UserDetail userDetail) {
        ReviewDTO reviewDTO = reviewService.getReview(boardId);

        model.addAttribute("reviewDTO", reviewDTO);
        model.addAttribute("userDetail", userDetail);
        return "community/review-detail";
    }

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
    @PostMapping("review-save")
    public void saveReply(@RequestParam ReviewReplyDTO reviewReplyDTO){
        reviewService.registerReply(reviewReplyDTO);
    }

    @DeleteMapping("revew-delete")
    public void deleteReviewReply(@RequestParam("replyId") Long replyId){reviewService.deleteReply(replyId);}

    @GetMapping("review-list")
    public Slice<ReplyDTO> getReviewList(@RequestParam("boardId") Long reviewId, @RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageable = PageRequest.of(page, 8);
        return reviewService.getReplyList(reviewId, pageable);
    }
}
