package com.kotlin.api.entity

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Getter
@Setter
@Entity
@Table(name = "person")
data class Person(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "NAME")
    val name: String,

    @Column(name = "SURNAME")
    val surname: String,

    @Column(name = "AGE")
    val age: Int

)
