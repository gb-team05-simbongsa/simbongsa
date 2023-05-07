package com.app.simbongsa.entity.volunteer;

import com.app.simbongsa.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(exclude = {"user", "volunteerWork"})
@Table(name = "TBL_VOLUNTEER_WORK_ACTIVITY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VolunteerWorkActivity {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VOLUNTEER_WORK_ID")
    private VolunteerWork volunteerWork;

    public VolunteerWorkActivity(User user, VolunteerWork volunteerWork) {
        this.user = user;
        this.volunteerWork = volunteerWork;
    }
}
