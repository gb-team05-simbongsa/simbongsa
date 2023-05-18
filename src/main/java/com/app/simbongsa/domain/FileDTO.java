package com.app.simbongsa.domain;

import com.app.simbongsa.entity.board.FreeBoard;
import com.app.simbongsa.entity.board.Review;
import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.type.FileRepresentationalType;
import lombok.Builder;
import lombok.Data;

@Data
//@Component
public class FileDTO {
    private FreeBoardDTO freeBoardDTO;
    private ReviewDTO reviewDTO;

    private Long id;
    private String fileName;
    private String fileUuid;
    private String filePath;
    private FileRepresentationalType fileRepresentationalType;

    private VolunteerWork volunteerWork;
    private Funding funding;
    private FreeBoard freeBoard;
    private Review review;

    @Builder
    public FileDTO(Long id, String fileName, String fileUuid, String filePath) {
        this.id = id;
        this.fileName = fileName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
    }
//    봉사활동
    @Builder
    public FileDTO(Long id, String fileName, String fileUuid, String filePath, FileRepresentationalType fileRepresentationalType, VolunteerWork volunteerWork) {
        this.id = id;
        this.fileName = fileName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.fileRepresentationalType = fileRepresentationalType;
        this.volunteerWork = volunteerWork;
    }
//    펀딩
    @Builder
    public FileDTO(Long id, String fileName, String fileUuid, String filePath, FileRepresentationalType fileRepresentationalType, Funding funding) {
        this.id = id;
        this.fileName = fileName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.fileRepresentationalType = fileRepresentationalType;
        this.funding = funding;
    }
//    자유게시판
    @Builder
    public FileDTO(Long id, String fileName, String fileUuid, String filePath, FileRepresentationalType fileRepresentationalType, FreeBoard freeBoard) {
        this.id = id;
        this.fileName = fileName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.fileRepresentationalType = fileRepresentationalType;
        this.freeBoard = freeBoard;
    }

//    활동 게시판
    @Builder
    public FileDTO(Long id, String fileName, String fileUuid, String filePath, FileRepresentationalType fileRepresentationalType, Review review) {
        this.id = id;
        this.fileName = fileName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.fileRepresentationalType = fileRepresentationalType;
        this.review = review;
    }

    public void setFreeBoardDTO(FreeBoardDTO freeBoardDTO){
        this.freeBoardDTO = freeBoardDTO;
    }

    public void setReviewDTO(ReviewDTO reviewDTO){
        this.reviewDTO = reviewDTO;
    }
}
