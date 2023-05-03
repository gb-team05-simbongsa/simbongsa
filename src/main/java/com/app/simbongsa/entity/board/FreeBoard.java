package com.app.simbongsa.entity.board;

import com.app.simbongsa.audit.Period;
import com.app.simbongsa.entity.file.File;
import com.app.simbongsa.entity.user.User;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @ToString
@Table(name = "TBL_FREE_BOARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeBoard extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String freeBoardTitle;
    @NotNull private String freeBoardContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

//    @OneToMany(fetch = FetchType.LAZY/*, mappedBy = "freeBoard"*/)
////    @JoinColumn(name = "FILE_ID")
//    private List<File> files;
}
