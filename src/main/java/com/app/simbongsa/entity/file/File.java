//package com.app.simbongsa.entity.file;
//
//import com.app.simbongsa.audit.Period;
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@Getter @Setter @ToString
//@Table(name = "TBL_FILE")
//@Inheritance(strategy = InheritanceType.JOINED)
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class File extends Period {
//    @Id @GeneratedValue
//    @EqualsAndHashCode.Include
//    private Long id;
//    private String name;
//    private String uuid;
//    private String filePath;
//
//    public File(String name, String uuid, String filePath) {
//        this.name = name;
//        this.uuid = uuid;
//        this.filePath = filePath;
//    }
//}
