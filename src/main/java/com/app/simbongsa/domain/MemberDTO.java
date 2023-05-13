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
@NoArgsConstructor
public class MemberDTO {
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


    @Builder
    public MemberDTO(Long id, String memberName, String memberEmail, String memberPassword, String memberAddress, Integer memberAge, String memberInterest, Role memberRole, MemberJoinType memberJoinType, UserRankType memberRank, int memberRice, int memberVolunteerTime, String randomKey, MemberStatus memberStatus) {
        this.id = id;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberAddress = memberAddress;
        this.memberAge = memberAge;
        this.memberInterest = memberInterest;
        this.memberRole = memberRole;
        this.memberJoinType = memberJoinType;
        this.memberRank = memberRank;
        this.memberRice = memberRice;
        this.memberVolunteerTime = memberVolunteerTime;
        this.randomKey = randomKey;
        this.memberStatus = memberStatus;
    }


}
