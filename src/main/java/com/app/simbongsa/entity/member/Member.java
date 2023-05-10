package com.app.simbongsa.entity.member;

import com.app.simbongsa.type.MemberJoinType;
import com.app.simbongsa.type.MemberStatus;
import com.app.simbongsa.type.Role;
import com.app.simbongsa.type.UserRankType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter @ToString
@Table(name = "TBL_MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class Member {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String memberName;
    @NotNull private String memberEmail;
    @NotNull private String memberPassword;
    private String memberAddress;
    private Integer memberAge;
    private String memberInterest;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'심청이'")
    private MemberJoinType memberJoinType;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'동냥1티어'")
    private UserRankType memberRank;
    @ColumnDefault("0")
    private int memberRice;
    @ColumnDefault("0")
    private int memberVolunteerTime;
    private String randomKey;
    @Enumerated(EnumType.STRING)
    @NotNull private Role memberRole;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'가입'")
    private MemberStatus memberStatus;


//    단위테스트용 생성자 생성 (봉사시간 추가)

    @Builder
    public Member(Long id, String memberName, String memberEmail, String memberPassword, String memberAddress, Integer memberAge, String memberInterest, MemberJoinType memberJoinType, UserRankType memberRank, int memberRice, int memberVolunteerTime, String randomKey, Role memberRole, MemberStatus memberStatus) {
        this.id = id;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberAddress = memberAddress;
        this.memberAge = memberAge;
        this.memberInterest = memberInterest;
        this.memberJoinType = memberJoinType;
        this.memberRank = memberRank;
        this.memberRice = memberRice;
        this.memberVolunteerTime = memberVolunteerTime;
        this.randomKey = randomKey;
        this.memberRole = memberRole;
        this.memberStatus = memberStatus;
    }
}
