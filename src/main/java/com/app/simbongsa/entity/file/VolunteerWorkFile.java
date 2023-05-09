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

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "VOLUNTEER_WORK_ID")
    private VolunteerWork volunteerWork;
}
