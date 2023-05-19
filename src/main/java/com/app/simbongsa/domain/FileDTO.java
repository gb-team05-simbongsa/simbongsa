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
    public FileDTO(Long id, String fileName, String fileUuid, String filePath, FileRepresentationalType fileRepresentationalType) {
        this.id = id;
        this.fileName = fileName;
        this.fileUuid = fileUuid;
        this.filePath = filePath;
        this.fileRepresentationalType = fileRepresentationalType;
    }

    public void setFreeBoardDTO(FreeBoardDTO freeBoardDTO){
        this.freeBoardDTO = freeBoardDTO;
    }

    public void setReviewDTO(ReviewDTO reviewDTO){
        this.reviewDTO = reviewDTO;
    }
}
