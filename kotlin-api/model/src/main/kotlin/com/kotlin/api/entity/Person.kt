package com.kotlin.api.entity

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import javax.persistence.*

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

) {
    constructor() : this(0,"","",0) {

    }
}
