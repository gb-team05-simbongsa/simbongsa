package com.app.simbongsa.domain;

import com.app.simbongsa.entity.member.Member;
import com.app.simbongsa.entity.volunteer.VolunteerWork;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
public class VolunteerWorkActivityDTO {
    private Long id;
    private LocalDate volunteerWorkActivityDate;

    private MemberDTO memberDTO;
    private VolunteerWorkDTO volunteerWorkDTO;

    @Builder
    public VolunteerWorkActivityDTO(Long id, LocalDate volunteerWorkActivityDate, MemberDTO memberDTO, VolunteerWorkDTO volunteerWorkDTO) {
        this.id = id;
        this.volunteerWorkActivityDate = volunteerWorkActivityDate;
        this.memberDTO = memberDTO;
        this.volunteerWorkDTO = volunteerWorkDTO;
    }
}
