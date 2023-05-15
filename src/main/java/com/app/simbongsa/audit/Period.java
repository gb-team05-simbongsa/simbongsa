package com.app.simbongsa.audit;

import com.querydsl.core.types.Expression;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public abstract class Period {
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @PrePersist
    public void create(){
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

    @PreUpdate
    public void update(){
        this.updatedDate = LocalDateTime.now();
    }

}