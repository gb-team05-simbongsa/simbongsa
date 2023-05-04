package com.app.simbongsa.entity.file;

import com.app.simbongsa.entity.support.SupportRequest;
import com.app.simbongsa.type.FileRepresentationalType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true, exclude = "supportRequest")
@Table(name = "TBL_SUPPORT_REQUEST_FILE")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SupportRequestFile extends File {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUPPORT_REQUEST_ID")
    private SupportRequest supportRequest;
}
