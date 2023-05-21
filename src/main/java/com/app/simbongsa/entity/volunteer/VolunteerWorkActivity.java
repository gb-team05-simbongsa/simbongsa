package com.app.simbongsa.entity.volunteer;

import com.app.simbongsa.entity.member.Member;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @ToString(exclude = {"member", "volunteerWork"})
@Table(name = "TBL_VOLUNTEER_WORK_ACTIVITY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VolunteerWorkActivity {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private LocalDate volunteerWorkActivityDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private VolunteerWork volunteerWork;
    
    
    public VolunteerWorkActivity(LocalDate volunteerWorkActivityDate, Member member, VolunteerWork volunteerWork) {
        this.volunteerWorkActivityDate = volunteerWorkActivityDate;
        this.member = member;
        this.volunteerWork = volunteerWork;
    }

    @Builder
    public VolunteerWorkActivity(Long id, LocalDate volunteerWorkActivityDate, Member member, VolunteerWork volunteerWork) {
        this.id = id;
        this.volunteerWorkActivityDate = volunteerWorkActivityDate;
        this.member = member;
        this.volunteerWork = volunteerWork;
    }


}
