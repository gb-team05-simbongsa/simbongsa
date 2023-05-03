package com.app.simbongsa.entity.file;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @ToString
@Table(name = "TBL_FILE_COLLECT")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileCollect {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILE_ID")
    private List<File> file;
}
