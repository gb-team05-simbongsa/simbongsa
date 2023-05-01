package com.app.simbongsa.entity.volunteer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_VOLUNTEER_WORK")
public class VolunteerWork {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private LocalDateTime volunteerWorkStartDate;
    private LocalDateTime volunteerWorkEndDate;
    private int volunteerWorkTime;
    private LocalDate volunteerWorkRecruitmentStartDate;
    private LocalDate volunteerWorkRecruitmentEndDate;
    private int volunteerWorkRecruitmentNumber;
    private String volunteerWorkField;
    private String volunteerWorkRegistrationAgency;
    private String volunteerWorkPlace;
}
