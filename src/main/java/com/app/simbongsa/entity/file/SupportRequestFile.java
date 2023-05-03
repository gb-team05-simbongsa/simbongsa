package com.app.simbongsa.entity.file;

import com.app.simbongsa.entity.support.SupportRequest;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString
@Table(name = "TBL_SUPPORT_REQUEST_FILE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SupportRequestFile extends FileCollect {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUPPORT_REQUEST_ID")
    private SupportRequest supportRequest;
}
