package com.app.simbongsa.controller;

import com.app.simbongsa.domain.*;
import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.service.board.FreeBoardService;
import com.app.simbongsa.service.board.ReviewService;
import com.app.simbongsa.type.FileRepresentationalType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/community/*")
@RequiredArgsConstructor
@Slf4j
public class CommunityController {
    private final FreeBoardService freeBoardService;
    private final ReviewService reviewService;

    @GetMapping("free-board")
    public String goFreeList(){return "community/free-board";}

    /*자유게시판 최신순*/
//    @GetMapping("free-board/new")
//    public String goFreeNewList(Model model, @PageableDefault(page=1, size=10) Pageable pageable) {
//        Slice<FreeBoardDTO> freeBoardDTOS = freeBoardService.getNewList(PageRequest.of(pageable.getPageNumber() - 1,
//                pageable.getPageSize()));
//        model.addAttribute("freeBoardList", freeBoardDTOS.getContent());
//        return "community/free-board";
//    }

    @GetMapping("free-board/newList")
    @ResponseBody
    public Slice<FreeBoardDTO> goToFreeNewList(@RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageRequest = PageRequest.of(page, 3);
        log.info(page + "=====================");
        return freeBoardService.getNewList(pageRequest);
    }
    @GetMapping("free-board/likes")
    @ResponseBody
    public Slice<FreeBoardDTO> goFreeLikesList(@RequestParam(defaultValue = "0", name = "page") int page){
        PageRequest pageRequest = PageRequest.of(page, 3);
        log.info(page + "=====================");
        return freeBoardService.getLikesList(pageRequest);
    }
//    @GetMapping("free-board")
//    public String goToFreeBoardList(){
//        return "community/free-board";
//    }

    /*자유게시판 인기순*/
//    @GetMapping("free-board/likes")
//    @ResponseBody
//    public Slice<FreeBoardDTO> goFreeLikesList(@PageableDefault(page=1, size=10) Pageable pageable){
//        Slice<FreeBoardDTO> freeBoardDTOS = freeBoardService.getLikesList(PageRequest.of(pageable.getPageNumber() - 1,
//                pageable.getPageSize()));
//        return freeBoardDTOS;
//    }

    /*자유게시판 작성하기*/
    @GetMapping("free-create")
    public String goToFreeCreate() {return "/community/free-create.html";}

    @PostMapping("free-create")
    @ResponseBody
    public void freeCreate(@RequestBody FreeBoardDTO freeBoardDTO, HttpSession session){
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        freeBoardDTO.setMemberDTO(memberDTO);

        freeBoardService.register(freeBoardDTO);
    }

    /*자유게시판 수정하기*/
    @GetMapping("free-board-modify/{boardId}")
    public String goToFreeModify(Model model, @PathVariable("boardID") Long boardId) {
        FreeBoardDTO freeBoardDTO = freeBoardService.getFreeBoard(boardId);
        return "community/free-board-modify";
    }
    @PostMapping("free-board-modify")
    public RedirectView modify(@RequestParam("boardId") Long boardId, @ModelAttribute("freeBoardDTO") FreeBoardDTO freeBoardDTO){
        freeBoardDTO.getFileDTOS().stream().forEach(fileDTO -> log.info(fileDTO.toString()));
        freeBoardDTO.setId(boardId);
        freeBoardService.update(freeBoardDTO);
        return new RedirectView("/community/free-detail" + boardId);
    }

    /*활동후기 수정하기*/
    @GetMapping("review-board-modify/{boardId}")
    public String goToReviewModify(Model model, @PathVariable("boardID") Long boardId) {
        ReviewDTO reviewDTO = reviewService.getReview(boardId);
        return "community/review-board-modify";
    }
    @PostMapping("review-board-modify/{boardId}")
    public RedirectView modify(@RequestParam("boardId") Long boardId, @ModelAttribute("reviewDTO") ReviewDTO reviewDTO){
        reviewDTO.getFileDTOS().stream().forEach(fileDTO -> log.info(fileDTO.toString()));
        reviewDTO.setId(boardId);
        reviewService.update(reviewDTO);
        return new RedirectView("/community/review-detail/{boardId}");
    }

    /*자유게시판 상세보가*/
    @GetMapping("free-detail/{id}")
    public String goToFreeDetail(Model model, @PathVariable Long id) {
        FreeBoardDTO freeBoardDTO = freeBoardService.getFreeBoardDetail(id);

        model.addAttribute("freeBoardDTO", freeBoardDTO);
        model.addAttribute("replyCount", freeBoardService.getCount(id));
        return "community/free-detail";
    }

    /*자유게시판 삭제하기*/
    @PostMapping("free-delete/{boardId}")
    public RedirectView freeDelete(@PathVariable("boardId") Long boardId) {
        freeBoardService.delete(boardId);
        return new RedirectView("/community/free-Board/newList");
    }


    /*활동후기 삭제하기*/
    @PostMapping("review-delete/{boardId}")
    public RedirectView reviewDelete(@PathVariable("boardId") Long boardId){
        reviewService.delete(boardId);
        return new RedirectView("/community/review-board/newList");
    }

/*=========================================================================================================================*/

    @GetMapping("review-board")
    public String goToReviewBoard(){return "community/review-board";}

    /*활동후기 최신순*/
    @GetMapping("review-board/new")
    public String getReviewNewList(Model model, @PageableDefault(page=1, size=10) Pageable pageable){
        Slice<ReviewDTO> reviewDTOS = reviewService.getNewReviewList(PageRequest.of(pageable.getPageNumber() - 1,
                pageable.getPageSize()));
        model.addAttribute("reviewBoardList", reviewDTOS.getContent());
        return "community/review-board";
    }

    @GetMapping("review-board/newList")
    @ResponseBody
    public List<ReviewDTO> getToReviewNewList(@PageableDefault(page=1, size=10) Pageable pageable){
        Slice<ReviewDTO> reviewDTOS = reviewService.getNewReviewList(PageRequest.of(pageable.getPageNumber() - 1,
                pageable.getPageSize()));
        return reviewDTOS.getContent();
    }

    /*황동후기 인기순*/
    @GetMapping("review-board/likes")
    @ResponseBody
    public List<ReviewDTO> getReviewLikesList(@PageableDefault(page=1, size=10) Pageable pageable){
        Slice<ReviewDTO> reviewDTOS = reviewService.getLikesReviewList(PageRequest.of(pageable.getPageNumber() - 1,
                pageable.getPageSize()));
        return reviewDTOS.getContent();
    }

    /*활동후기 작성하기*/
    @GetMapping("review-create")
    public void goToReviewCreate(ReviewDTO reviewDTO) {}

    @PostMapping("review-create")
    @ResponseBody
    public void reviewCreate(@RequestBody ReviewDTO reviewDTO, HttpSession session){
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        reviewDTO.setMemberDTO(memberDTO);

        reviewService.register(reviewDTO);
    }

    /*활동후기 상세보기*/
    @GetMapping("review-detail/{id}")
    public String goToReviewDerail(Model model, @PathVariable Long id) {
        ReviewDTO reviewDTO = reviewService.getReviewDetail(id);
        model.addAttribute("reviewDTO", reviewDTO);
        return "community/review-detail";
    }

}
