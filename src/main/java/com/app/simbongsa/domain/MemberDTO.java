package com.app.simbongsa.domain;

import com.app.simbongsa.type.MemberJoinType;
import com.app.simbongsa.type.MemberStatus;
import com.app.simbongsa.type.Role;
import com.app.simbongsa.type.UserRankType;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

//@Component
@Data
@Builder
public class MemberDTO {
    @NotNull
    private Long id;
    private String memberName;
    private String memberEmail;
    private String memberPassword;
    private String memberAddress;
    private Integer memberAge;
    private String memberInterest;
    private Role memberRole;
    private MemberJoinType memberJoinType;
    private UserRankType memberRank;
    private int memberRice;
    private int memberVolunteerTime;
    private String randomKey;
    private MemberStatus memberStatus;

}
