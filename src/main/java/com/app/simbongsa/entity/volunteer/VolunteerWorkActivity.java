package com.app.simbongsa.entity.volunteer;

import com.app.simbongsa.entity.user.User;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @ToString(exclude = {"user", "volunteerWork"})
@Table(name = "TBL_VOLUNTEER_WORK_ACTIVITY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VolunteerWorkActivity {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private LocalDate volunteerWorkActivityDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VOLUNTEER_WORK_ID")
    private VolunteerWork volunteerWork;
    
    
    public VolunteerWorkActivity(LocalDate volunteerWorkActivityDate, User user, VolunteerWork volunteerWork) {
        this.volunteerWorkActivityDate = volunteerWorkActivityDate;
        this.user = user;
        this.volunteerWork = volunteerWork;
    }
}
