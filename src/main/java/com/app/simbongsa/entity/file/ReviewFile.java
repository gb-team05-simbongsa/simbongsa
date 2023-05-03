package com.app.simbongsa.entity.file;

import com.app.simbongsa.entity.board.Review;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString
@Table(name = "TBL_REVIEW_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewFile extends FileCollect {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REVIEW_ID")
    private Review review;
}
