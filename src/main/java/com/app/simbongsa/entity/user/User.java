package com.app.simbongsa.entity.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_USER")
public class User {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userAddress;
    private Integer userAge;
    private String userInterest;
    private String userStatus;
    private String userRank;
    private int userRice;
    private int userVolunteerTime;
}
