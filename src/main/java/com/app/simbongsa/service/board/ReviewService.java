package com.app.simbongsa.service.board;

import com.app.simbongsa.domain.FileDTO;
import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.domain.ReviewDTO;
import com.app.simbongsa.entity.board.FreeBoard;
import com.app.simbongsa.entity.board.Review;
import com.app.simbongsa.entity.file.FreeBoardFile;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.search.admin.AdminBoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ReviewService {

    /*저징*/
    public void register(ReviewDTO reviewDTO, Long memberId);

    /*상세보기*/
    public ReviewDTO getReview(Long reviewId);

    /*작성하기*/
    public void write(Review review);

    /*최신순 목록*/
    public Slice<ReviewDTO> getNewReviewList(Pageable pageable);

    /*인기순 목록*/
    public Slice<ReviewDTO> getLikesReviewList(Pageable pageable);

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
                .createdDate(review.getCreatedDate())
                .updatedDate(review.getUpdatedDate())
                .memberDTO(toMemberDTO(review.getMember()))
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

    default FileDTO fileToDTO(FreeBoardFile freeBoardFile){
        return FileDTO.builder()
                .fileUuid(freeBoardFile.getFileUuid())
                .filePath(freeBoardFile.getFilePath())
                .fileName(freeBoardFile.getFileName())
                .build();
    }

    default Review toReviewEntity(ReviewDTO reviewDTO){
        return Review.builder()
                .id(reviewDTO.getId())
                .boardTitle(reviewDTO.getBoardTitle())
                .boardContent(reviewDTO.getBoardContent())
                .member(toMemberEntity(reviewDTO.getMemberDTO()))
                .build();
    }

    default Member toMemberEntity(MemberDTO memberDTO){
        return Member.builder()
                .id(memberDTO.getId())
                .memberRank(memberDTO.getMemberRank())
                .memberName(memberDTO.getMemberName())
                .memberJoinType(memberDTO.getMemberJoinType())
                .build();
    }

}
