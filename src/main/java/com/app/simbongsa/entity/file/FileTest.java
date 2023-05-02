package com.app.simbongsa.entity.file;

import com.app.simbongsa.entity.board.FreeBoard;
import com.app.simbongsa.entity.board.Review;
import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.entity.support.SupportRequest;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.type.FileRepresentationalType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileTest {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long Id;
    @NotNull private String name;
    @NotNull private String uuid;
    @NotNull private String filePath;

//    대표 이미지 검사
    @Enumerated(EnumType.STRING)
    @NotNull private FileRepresentationalType fileRepresentationalType;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "FUNDING_ID")
    private Funding funding;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "FREE_BOARD_ID")
    private FreeBoard freeBoard;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "REVIEW_ID")
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "SUPPORT_REQUEST_ID")
    private SupportRequest supportRequest;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "VOLUNTEER_WORK_ID")
    private VolunteerWork volunteerWork;
}
