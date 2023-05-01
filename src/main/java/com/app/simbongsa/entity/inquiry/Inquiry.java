package com.app.simbongsa.entity.inquiry;

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
@Table(name = "TBL_INQUIRY")
public class Inquiry {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String inquiryTitle;
    private String inquiryContent;

//    private Long user_id;
}
