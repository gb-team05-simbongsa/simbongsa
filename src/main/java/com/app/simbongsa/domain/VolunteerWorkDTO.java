package com.app.simbongsa.domain;

import com.app.simbongsa.entity.file.VolunteerWorkFile;
import com.app.simbongsa.type.VolunteerWorkCategoryType;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data
public class VolunteerWorkDTO {
    private Long id;
    private LocalDateTime volunteerWorkStartDate;
    private LocalDateTime volunteerWorkEndDate;
    private int volunteerWorkTime;
    private LocalDate volunteerWorkJoinStartDate;
    private LocalDate volunteerWorkJoinEndDate;
    private int volunteerWorkRecruitNumber;
    private VolunteerWorkCategoryType volunteerWorkCategory;
    private String volunteerWorkRegisterAgency;
    private String volunteerWorkPlace;
    private String volunteerWorkTitle;
    private String volunteerWorkContent;
    private FileDTO fileDTO;

    public VolunteerWorkDTO(FileDTO fileDTO) {
        this.fileDTO = fileDTO;
    }

    @Builder
    public VolunteerWorkDTO(Long id, LocalDateTime volunteerWorkStartDate, LocalDateTime volunteerWorkEndDate, int volunteerWorkTime, LocalDate volunteerWorkJoinStartDate, LocalDate volunteerWorkJoinEndDate, int volunteerWorkRecruitNumber, VolunteerWorkCategoryType volunteerWorkCategory, String volunteerWorkRegisterAgency, String volunteerWorkPlace, String volunteerWorkTitle, String volunteerWorkContent, FileDTO fileDTO) {
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
        this.volunteerWorkContent = volunteerWorkContent;
        this.fileDTO = fileDTO;
    }

}
