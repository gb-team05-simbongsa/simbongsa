package com.app.simbongsa.entity.file;

import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.app.simbongsa.type.FileRepresentationalType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true, exclude = "volunteerWork")
@Table(name = "TBL_VOLUNTEER_WORK_FILE")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VolunteerWorkFile extends File {

    @OneToOne(fetch = FetchType.LAZY)
    private VolunteerWork volunteerWork;


    @Builder
    public VolunteerWorkFile(Long id, String fileName, String fileUuid, String filePath, FileRepresentationalType fileRepresentationalType, VolunteerWork volunteerWork) {
        super(id, fileName, fileUuid, filePath, fileRepresentationalType);
        this.volunteerWork = volunteerWork;
    }
}
