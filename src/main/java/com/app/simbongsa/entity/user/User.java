package com.app.simbongsa.entity.user;

import com.app.simbongsa.type.UserJoinType;
import com.app.simbongsa.type.UserRankType;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter @ToString
@Table(name = "TBL_USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
public class User {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String userName;
    @NotNull private String userEmail;
    @NotNull private String userPassword;
    private String userAddress;
    private Integer userAge;
    private String userInterest;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'일반'")
    private UserJoinType userStatus;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'동냥1티어'")
    private UserRankType userRank;
    @ColumnDefault("0")
    private int userRice;
    @ColumnDefault("0")
    private int userVolunteerTime;

//    단위테스트용 생성자 생성

    public User(String userName, String userEmail, String userPassword, String userAddress, Integer userAge, String userInterest) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userAddress = userAddress;
        this.userAge = userAge;
        this.userInterest = userInterest;
    }
}
