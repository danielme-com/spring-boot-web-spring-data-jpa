package com.danielme.springboot.entities;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners({ AuditingEntityListener.class })
@MappedSuperclass
public class AuditableEntity {

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Calendar createdDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Calendar lastModifiedDate;

    @CreatedBy
    @Column(nullable = false)
    private String createBy;

    @LastModifiedBy
    private String lastModifiedBy;

    public Calendar getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Calendar createdDate) {
        this.createdDate = createdDate;
    }

    public Calendar getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Calendar lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

}
