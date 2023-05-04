package com.app.simbongsa.entity.file;

import com.app.simbongsa.entity.board.FreeBoard;
import com.app.simbongsa.entity.funding.Funding;
import com.app.simbongsa.type.FileRepresentationalType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @ToString(callSuper = true, exclude = "funding")
@Table(name = "TBL_FUNDING_FILE")
@PrimaryKeyJoinColumn(name = "ID")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FundingFile extends File{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FUNDING_ID")
    private Funding funding;
}
