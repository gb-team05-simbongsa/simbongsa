package com.app.simbongsa.entity.volunteer;

import com.app.simbongsa.type.VolunteerWorkCategoryType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_VOLUNTEER_WORK")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VolunteerWork {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private LocalDateTime volunteerWorkStartDate;
    @NotNull private LocalDateTime volunteerWorkEndDate;
    @NotNull private int volunteerWorkTime;
    @NotNull private LocalDate volunteerWorkRecruitmentStartDate;
    @NotNull private LocalDate volunteerWorkRecruitmentEndDate;
    @NotNull private int volunteerWorkRecruitmentNumber;
    @Enumerated(EnumType.STRING)
    @NotNull private VolunteerWorkCategoryType volunteerWorkCategory;
    @NotNull private String volunteerWorkRegistrationAgency;
    @NotNull private String volunteerWorkPlace;

    @Builder
    public VolunteerWork(LocalDateTime volunteerWorkStartDate, LocalDateTime volunteerWorkEndDate, int volunteerWorkTime, LocalDate volunteerWorkRecruitmentStartDate, LocalDate volunteerWorkRecruitmentEndDate, int volunteerWorkRecruitmentNumber, VolunteerWorkCategoryType volunteerWorkCategory, String volunteerWorkRegistrationAgency, String volunteerWorkPlace) {
        this.volunteerWorkStartDate = volunteerWorkStartDate;
        this.volunteerWorkEndDate = volunteerWorkEndDate;
        this.volunteerWorkTime = volunteerWorkTime;
        this.volunteerWorkRecruitmentStartDate = volunteerWorkRecruitmentStartDate;
        this.volunteerWorkRecruitmentEndDate = volunteerWorkRecruitmentEndDate;
        this.volunteerWorkRecruitmentNumber = volunteerWorkRecruitmentNumber;
        this.volunteerWorkCategory = volunteerWorkCategory;
        this.volunteerWorkRegistrationAgency = volunteerWorkRegistrationAgency;
        this.volunteerWorkPlace = volunteerWorkPlace;
    }
}
