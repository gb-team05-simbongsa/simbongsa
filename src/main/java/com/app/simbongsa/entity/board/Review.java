package com.app.simbongsa.entity.board;

import com.app.simbongsa.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString
@Table(name = "TBL_REVIEW")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends Board {
//    @Id @GeneratedValue
//    @EqualsAndHashCode.Include
//    private Long id;
//    @NotNull private String reviewTitle;
//    @NotNull private String reviewContent;
//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "review")
////    @JoinColumn(name = "FILE_ID")
//    private List<File> files;
}
