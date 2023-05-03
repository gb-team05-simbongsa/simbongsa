package com.app.simbongsa.entity.file;

import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.type.FileRepresentationalType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString
@Table(name = "TBL_VOLUNTEER_WORK_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VolunteerWorkFile {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String fileName;
    private String fileUuid;
    private String filePath;

    //    대표 이미지 검사
    @Enumerated(EnumType.STRING)
    @NotNull
    private FileRepresentationalType fileRepresentationalType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VOLUNTEER_WORK_ID")
    private VolunteerWork volunteerWork;
}
