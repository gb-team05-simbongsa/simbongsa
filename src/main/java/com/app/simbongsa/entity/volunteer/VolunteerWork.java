package com.app.simbongsa.entity.volunteer;

import com.app.simbongsa.type.VolunteerWorkCategoryType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter @ToString
@Table(name = "TBL_VOLUNTEER_WORK")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class VolunteerWork {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private LocalDateTime volunteerWorkStartDate;
    @NotNull private LocalDateTime volunteerWorkEndDate;
    @NotNull private int volunteerWorkTime;
    @NotNull private LocalDate volunteerWorkJoinStartDate;
    @NotNull private LocalDate volunteerWorkJoinEndDate;
    @NotNull private int volunteerWorkRecruitNumber;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'선택안함'")
    @NotNull private VolunteerWorkCategoryType volunteerWorkCategory;
    @NotNull private String volunteerWorkRegisterAgency;
    @NotNull private String volunteerWorkPlace;
}
