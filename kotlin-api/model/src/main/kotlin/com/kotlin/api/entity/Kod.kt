package com.kotlin.api.entity

import com.kotlin.api.base.BaseEntity
import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Getter
@Setter
@Entity
@Table(name = "KOD")
class Kod : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long? = 0

    @Column(name = "NAME")
    private val name: String? = ""
}