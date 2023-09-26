package com.kotlin.api.base

import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.Getter
import lombok.Setter
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import java.util.*
import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

@Getter
@Setter
@MappedSuperclass
open class BaseEntity {

    @JsonIgnore
    @Column(name = "createdDate", updatable = false)
    private var createdDate: Date? = null

    @JsonIgnore
    @Column(name = "updatedDate", updatable = false)
    private var updatedDate: Date? = null

    @JsonIgnore
    @Column(name = "isDeleted", nullable = false)
    private var isDeleted: Long? = null

    @JsonIgnore
    @CreatedBy
    @Column(name = "createdBy")
    private var createdBy: String? = null

    @JsonIgnore
    @LastModifiedBy
    @Column(name = "updatedBy")
    private var updatedBy: String? = null

    @PreUpdate
    fun setPreUpdate() {
        updatedDate = Date()
    }

    @PrePersist
    fun setPrePersist() {
        createdDate = Date()
        this.isDeleted = 0
    }
}