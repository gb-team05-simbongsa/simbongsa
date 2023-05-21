package com.app.simbongsa.service.board;

import com.app.simbongsa.domain.*;
import com.app.simbongsa.entity.board.FreeBoard;
import com.app.simbongsa.entity.board.FreeBoardReply;
import com.app.simbongsa.entity.file.FreeBoardFile;
import com.app.simbongsa.entity.file.FundingFile;
import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.provider.UserDetail;
import com.app.simbongsa.search.admin.AdminBoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.ArrayList;
import java.util.List;

public interface FreeBoardService {

    // 저장
    public void register(FreeBoardDTO freeBoardDTO, Long memberId);

    // 상세 보기
    public FreeBoardDTO getFreeBoard(Long freeBoardId);

    // 시퀀스 가져오기
    public FreeBoard getCurrentSequence();

    // 댓글 저장
    public void registerReply(FreeBoardReplyDTO freeBoardReplyDTO);

    // 댓글 삭제
    public void deleteReply(Long replyId);

    // 댓글 목록
    public Slice<ReplyDTO> getReplyList(Long freeBoardId, Pageable pageable);

    // 댓글 갯수
    public Integer getReplyCount(Long freeBoardId);

    // 최신순 목록
    public Slice<FreeBoardDTO> getNewList(Pageable pageable);

    // 인기순 목록
    public Slice<FreeBoardDTO> getLikesList(Pageable pageable);

    // 작성하기
    public void write(FreeBoardDTO freeBoardDTO, Long memberId);

//    목록 전체 조회(페이징)
    public Page<FreeBoardDTO> getFreeBoard(Integer page, AdminBoardSearch adminBoardSearch);

//    게시판 상세보기
    public FreeBoardDTO getFreeBoardDetail(Long id);

//    게시판 삭제
    public void deleteFreeBoardByIds(List<Long> ids);

//    게시판 인기순 조회 - 메인 페이지
    public List<FreeBoardDTO> getAllWithPopularFreeBoard();
    public List<FreeBoardDTO> getAllWithFile();

//    유저가 작성한 자유게시물 조회(페이징처리)
    Page<FreeBoardDTO> getMyFreeBoards(Integer page, MemberDTO memberDTO);


    default FreeBoardDTO toFreeBoardDTO(FreeBoard freeBoard) {
        return FreeBoardDTO.builder()
                .id(freeBoard.getId())
                .boardTitle(freeBoard.getBoardTitle())
                .boardContent(freeBoard.getBoardContent())
                .createdDate(freeBoard.getCreatedDate())
                .updatedDate(freeBoard.getUpdatedDate())
                .memberDTO(toMemberDTO(freeBoard.getMember()))
                .replyCount(freeBoard.getFreeBoardReplyCount())
                .build();
    }
    default List<FileDTO> FileToDTO(List<FreeBoardFile> freeBoardFiles){
        List<FileDTO> freeBoardFileList = new ArrayList<>();
        freeBoardFiles.forEach(
                freeBoardFile -> {
                    FileDTO fileDTO = FileDTO.builder()
                            .id(freeBoardFile.getId())
                            .fileName(freeBoardFile.getFileName())
                            .filePath(freeBoardFile.getFilePath())
                            .fileUuid(freeBoardFile.getFileUuid())
                            .build();
                    freeBoardFileList.add(fileDTO);
                }
        );
        return freeBoardFileList;
    }
    default List<FreeBoardFile> toFreeBoardListEntity(List<FileDTO> fileDTOS){
        List<FreeBoardFile> freeBoardFiles = new ArrayList<>();

        fileDTOS.forEach(
                fileDTO ->{
                    FreeBoardFile freeBoardFile = FreeBoardFile.builder()
                            .fileName(fileDTO.getFileName())
                            .filePath(fileDTO.getFilePath())
                            .fileUuid(fileDTO.getFileUuid())
                            .build();
                    freeBoardFiles.add(freeBoardFile);
                }
        );
        return freeBoardFiles;
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


    default FreeBoardDTO freeBoardToDTO(FreeBoard freeBoard){
        return FreeBoardDTO.builder()
                .id(freeBoard.getId())
                .memberDTO(toMemberDTO(freeBoard.getMember()))
                .createdDate(freeBoard.getCreatedDate())
                .updatedDate(freeBoard.getUpdatedDate())
                .boardTitle(freeBoard.getBoardTitle())
                .boardContent(freeBoard.getBoardContent())
                .replyCount(freeBoard.getFreeBoardReplyCount())
                .build();
    }

    default Member toMemberEntity(MemberDTO memberDTO){
        return Member.builder()
                .id(memberDTO.getId())
                .memberName(memberDTO.getMemberName())
                .memberJoinType(memberDTO.getMemberJoinType())
                .memberRank(memberDTO.getMemberRank())
                .memberAddress(memberDTO.getMemberAddress())
                .memberAge(memberDTO.getMemberAge())
                .memberEmail(memberDTO.getMemberEmail())
                .memberPassword(memberDTO.getMemberPassword())
                .memberRice(memberDTO.getMemberRice())
                .memberInterest(memberDTO.getMemberInterest())
                .memberRole(memberDTO.getMemberRole())
                .memberStatus(memberDTO.getMemberStatus())
                .memberVolunteerTime(memberDTO.getMemberVolunteerTime())
                .randomKey(memberDTO.getRandomKey())
                .build();
    }

    default FreeBoard toFreeBoardEntity(FreeBoardDTO freeBoardDTO){
        return FreeBoard.builder()
                .boardTitle(freeBoardDTO.getBoardTitle())
                .boardContent(freeBoardDTO.getBoardContent())
                .member(toMemberEntity(freeBoardDTO.getMemberDTO()))
                .build();
    }

    default FreeBoardFile toFreeBoardFileEntity(FileDTO fileDTO){
        return FreeBoardFile.builder()
//                .id(fileDTO.getId())
                .fileName(fileDTO.getFileName())
                .fileUuid(fileDTO.getFileUuid())
                .filePath(fileDTO.getFilePath())
                .freeBoard(fileDTO.getFreeBoard())
                .fileRepresentationalType(fileDTO.getFileRepresentationalType())
                .build();
    }

    default ReplyDTO toReplyDTO(FreeBoardReply freeBoardReply){
        return ReplyDTO.builder()
                .id(freeBoardReply.getId())
                .memberDTO(toMemberDTO(freeBoardReply.getMember()))
                .registerDate(freeBoardReply.getCreatedDate())
                .replyContent(freeBoardReply.getReplyContent())
                .build();
    }

}
