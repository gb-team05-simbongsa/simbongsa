package com.app.simbongsa.controller;

import com.app.simbongsa.domain.FreeBoardReplyDTO;
import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.domain.ReplyDTO;
import com.app.simbongsa.domain.ReplyRequestDTO;
import com.app.simbongsa.service.board.FreeBoardService;
import com.app.simbongsa.service.board.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/community/*")
@RequiredArgsConstructor
@Slf4j
public class CommunityRestController {
    private final FreeBoardService freeBoardService;
    private final ReviewService reviewService;

    /* 자유게시판 댓글*/
//    @PostMapping("save")
//    public void saveReply(String replyContent,  Long id, HttpSession session){
//        log.info(replyContent + "=====================================");
//        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
//
//        FreeBoardReplyDTO freeBoardReplyDTO = new FreeBoardReplyDTO();
//        freeBoardReplyDTO.setFreeBoardReplyContent(replyContent);
//        freeBoardReplyDTO.setFreeBoardDTO(freeBoardService.getFreeBoardDetail(id));
//        freeBoardReplyDTO.setMemberDTO(memberDTO);
//
//        freeBoardService.insertReply(freeBoardReplyDTO);
//    }

    @PostMapping("save")
    public void saveReply(@RequestBody ReplyRequestDTO replyRequestDTO){
        freeBoardService.insertReply(replyRequestDTO);
    }

    @DeleteMapping("delete")
    public void deleteFreeReply(@RequestParam("replyId") Long replyId){freeBoardService.deleteReply(replyId);}

    @PostMapping("list")
    public Slice<FreeBoardReplyDTO> getFreeList(Long boardId, int page){
        Slice<FreeBoardReplyDTO> replyList = freeBoardService.getReplyList(boardId, page);
        log.info(replyList.toString());
        return replyList;
    }


    /*활동후기 댓글*/
    @PostMapping("review-save")
    public void registerReply(@RequestParam ReplyRequestDTO replyRequestDTO){
        reviewService.registerReply(replyRequestDTO);
    }

    @DeleteMapping("revew-delete")
    public void deleteReviewReply(@RequestParam("replyId") Long replyId){reviewService.deleteReply(replyId);}

    @GetMapping("review-list")
    public Slice<ReplyDTO> getReviewList(@RequestParam("boardId") Long reviewId, @RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageable = PageRequest.of(page, 5);
        return reviewService.getReplyList(reviewId, pageable);
    }
}
