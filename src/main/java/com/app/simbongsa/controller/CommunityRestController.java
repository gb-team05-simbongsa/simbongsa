package com.app.simbongsa.controller;

import com.app.simbongsa.domain.*;
import com.app.simbongsa.service.board.FreeBoardService;
import com.app.simbongsa.service.board.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/communities/*")
@RequiredArgsConstructor
@Slf4j
public class CommunityRestController {
    private final FreeBoardService freeBoardService;
    private final ReviewService reviewService;

    /* 자유게시판 댓글*/
    @PostMapping("save")
    public void saveReply(@RequestBody ReplyRequestDTO replyRequestDTO){
        freeBoardService.insertReply(replyRequestDTO);
    }

    @DeleteMapping("delete")
    public void deleteFreeReply(Long replyId){freeBoardService.deleteReply(replyId);}

    @PostMapping("list")
    public Slice<FreeBoardReplyDTO> getFreeList(Long boardId, int page){
        Slice<FreeBoardReplyDTO> replyList = freeBoardService.getReplyList(boardId, page);
        log.info(replyList.toString());
        return replyList;
    }


    /*활동후기 댓글*/
    @PostMapping("review-save")
    public void registerReply(@RequestBody ReplyRequestDTO replyRequestDTO){
        reviewService.registerReply(replyRequestDTO);
    }

    @DeleteMapping("revew-delete")
    public void deleteReviewReply(Long replyId){reviewService.deleteReply(replyId);}

    @PostMapping("review-list")
    public Slice<ReviewReplyDTO> getReviewList(Long boardId, int page){
        Slice<ReviewReplyDTO> replyList = reviewService.getReplyList(boardId, page);
        return replyList;
    }
}
