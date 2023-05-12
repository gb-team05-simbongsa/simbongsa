package com.app.simbongsa.service.board;

import com.app.simbongsa.domain.FreeBoardDTO;
import com.app.simbongsa.domain.MemberDTO;
import com.app.simbongsa.entity.board.FreeBoard;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.search.admin.AdminBoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface FreeBoardService {
    // 저장
    public void register(FreeBoardDTO freeBoardDTO, Long memberId);

    // 최신순 목록
    public Slice<FreeBoardDTO> getNewList(Pageable pageable);

    // 인기순 목록
    public Slice<FreeBoardDTO> getLikesList(Pageable pageable);

    // 상세 보기
    public FreeBoardDTO getDetail(Long memberId);

    // 작성
    public void write(FreeBoard freeBoard);

//    목록 전체 조회(페이징)
    public Page<FreeBoardDTO> getFreeBoard(Integer page, AdminBoardSearch adminBoardSearch);

//    게시판 상세보기
    public FreeBoardDTO getFreeBoardDetail(Long id);

//    게시판 삭제
    public void deleteFreeBoardByIds(List<Long> ids);

    default FreeBoardDTO toFreeBoardDTO(FreeBoard freeBoard) {
        return FreeBoardDTO.builder()
                .id(freeBoard.getId())
                .boardTitle(freeBoard.getBoardTitle())
                .boardContent(freeBoard.getBoardContent())
                .memberDTO(toMemberDTO(freeBoard.getMember()))
                .freeBoardReplies(freeBoard.getFreeBoardReplies())
                .freeBoardFiles(freeBoard.getFreeBoardFiles())
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
