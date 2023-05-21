package com.app.simbongsa.entity.file;

import com.app.simbongsa.entity.board.Review;
import com.app.simbongsa.type.FileRepresentationalType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true, exclude = "review")
@Table(name = "TBL_REVIEW_FILE")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewFile extends File {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REVIEW_ID")
    private Review review;

    @Builder
    public ReviewFile(Long id, String fileName, String fileUuid, String filePath, FileRepresentationalType fileRepresentationalType, Review review) {
        super(id, fileName, fileUuid, filePath, fileRepresentationalType);
        this.review = review;
    }

    public void setReview(Review review){
        this.review = review;
    }

}
