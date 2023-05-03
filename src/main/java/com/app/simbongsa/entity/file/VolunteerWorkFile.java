package com.app.simbongsa.entity.file;

import com.app.simbongsa.entity.volunteer.VolunteerWork;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString
@Table(name = "TBL_VOLUNTEER_WORK_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VolunteerWorkFile extends FileCollect {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VOLUNTEER_WORK_ID")
    private VolunteerWork volunteerWork;
}
