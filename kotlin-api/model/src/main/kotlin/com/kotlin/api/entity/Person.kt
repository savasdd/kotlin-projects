package com.kotlin.api.entity

import com.kotlin.api.base.BaseEntity
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
class Person : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0

    @Column(name = "NAME")
    var name: String? = null

    @Column(name = "SURNAME")
    var surname: String? = null

    @Column(name = "AGE")
    var age: Int? = null
}
