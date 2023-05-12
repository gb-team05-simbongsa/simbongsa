package com.app.simbongsa.service.board;

import com.app.simbongsa.domain.FreeBoardDTO;
import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.domain.ReviewDTO;
import com.app.simbongsa.entity.board.FreeBoard;
import com.app.simbongsa.entity.board.Review;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.search.admin.AdminBoardSearch;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewService {

//    목록 전체 조회(페이징)
    public Page<ReviewDTO> getReview(Integer page, AdminBoardSearch adminBoardSearch);

//    게시판 상세보기
    public ReviewDTO getReviewDetail(Long id);

//    게시판 삭제
    public void deleteReviewByIds(List<Long> ids);


    default ReviewDTO toReviewDTO(Review review) {
        return ReviewDTO.builder()
                .id(review.getId())
                .boardTitle(review.getBoardTitle())
                .boardContent(review.getBoardContent())
                .memberDTO(toMemberDTO(review.getMember()))
                .reviewReplies(review.getReviewReplies())
                .reviewFiles(review.getReviewFiles())
                .build();
    }

    default MemberDTO toMemberDTO(Member member){
        return MemberDTO.builder()
                .id(member.getId())
                .memberRank(member.getMemberRank())
                .memberName(member.getMemberName())
                .memberVolunteerTime(member.getMemberVolunteerTime())
                .memberAddress(member.getMemberAddress())
                .memberEmail(member.getMemberEmail())
                .memberAge(member.getMemberAge())
                .memberPassword(member.getMemberPassword())
                .memberInterest(member.getMemberInterest())
                .memberJoinType(member.getMemberJoinType())
                .memberRice(member.getMemberRice())
                .memberRole(member.getMemberRole())
                .memberStatus(member.getMemberStatus())
                .build();
    }
}
