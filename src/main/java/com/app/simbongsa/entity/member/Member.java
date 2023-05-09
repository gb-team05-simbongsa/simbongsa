package com.app.simbongsa.entity.member;

import com.app.simbongsa.type.UserJoinType;
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
    @ColumnDefault("'일반'")
    private UserJoinType memberStatus;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'동냥1티어'")
    private UserRankType memberRank;
    @ColumnDefault("0")
    private int memberRice;
    @ColumnDefault("0")
    private int memberVolunteerTime;

//    단위테스트용 생성자 생성 (봉사시간 추가)

    public Member(String memberName, String memberEmail, String memberPassword, String memberAddress, Integer memberAge, String memberInterest, int memberVolunteerTime, int memberRice) {
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberAddress = memberAddress;
        this.memberAge = memberAge;
        this.memberInterest = memberInterest;
        this.memberVolunteerTime = memberVolunteerTime;
        this.memberRice = memberRice;
    }
}
