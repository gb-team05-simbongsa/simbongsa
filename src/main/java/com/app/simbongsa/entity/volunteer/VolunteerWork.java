package com.app.simbongsa.entity.volunteer;

import com.app.simbongsa.entity.file.VolunteerWorkFile;
import com.app.simbongsa.type.VolunteerWorkCategoryType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @ToString(exclude = "volunteerWorkFiles")
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
    @ColumnDefault("'헌혈'")
    private VolunteerWorkCategoryType volunteerWorkCategory;
    @NotNull private String volunteerWorkRegisterAgency;
    @NotNull private String volunteerWorkPlace;
    @NotNull private String volunteerWorkTitle;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "volunteerWork", cascade=CascadeType.REMOVE)
    private List<VolunteerWorkFile> volunteerWorkFiles;
//    단위테스트용 생성자 생성

    @Builder
    public VolunteerWork(LocalDateTime volunteerWorkStartDate, LocalDateTime volunteerWorkEndDate, int volunteerWorkTime, LocalDate volunteerWorkJoinStartDate, LocalDate volunteerWorkJoinEndDate, int volunteerWorkRecruitNumber, String volunteerWorkRegisterAgency, String volunteerWorkPlace, String volunteerWorkTitle) {
        this.volunteerWorkStartDate = volunteerWorkStartDate;
        this.volunteerWorkEndDate = volunteerWorkEndDate;
        this.volunteerWorkTime = volunteerWorkTime;
        this.volunteerWorkJoinStartDate = volunteerWorkJoinStartDate;
        this.volunteerWorkJoinEndDate = volunteerWorkJoinEndDate;
        this.volunteerWorkRecruitNumber = volunteerWorkRecruitNumber;
        this.volunteerWorkRegisterAgency = volunteerWorkRegisterAgency;
        this.volunteerWorkPlace = volunteerWorkPlace;
        this.volunteerWorkTitle = volunteerWorkTitle;
    }

    @Builder
    public VolunteerWork(Long id, LocalDateTime volunteerWorkStartDate, LocalDateTime volunteerWorkEndDate, int volunteerWorkTime, LocalDate volunteerWorkJoinStartDate, LocalDate volunteerWorkJoinEndDate, int volunteerWorkRecruitNumber, VolunteerWorkCategoryType volunteerWorkCategory, String volunteerWorkRegisterAgency, String volunteerWorkPlace, String volunteerWorkTitle, List<VolunteerWorkFile> volunteerWorkFiles) {
        this.id = id;
        this.volunteerWorkStartDate = volunteerWorkStartDate;
        this.volunteerWorkEndDate = volunteerWorkEndDate;
        this.volunteerWorkTime = volunteerWorkTime;
        this.volunteerWorkJoinStartDate = volunteerWorkJoinStartDate;
        this.volunteerWorkJoinEndDate = volunteerWorkJoinEndDate;
        this.volunteerWorkRecruitNumber = volunteerWorkRecruitNumber;
        this.volunteerWorkCategory = volunteerWorkCategory;
        this.volunteerWorkRegisterAgency = volunteerWorkRegisterAgency;
        this.volunteerWorkPlace = volunteerWorkPlace;
        this.volunteerWorkTitle = volunteerWorkTitle;
    }
}
