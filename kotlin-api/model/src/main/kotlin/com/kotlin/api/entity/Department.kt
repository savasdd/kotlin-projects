package com.kotlin.api.entity

import com.kotlin.api.base.BaseEntity
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import org.hibernate.annotations.Where
import javax.persistence.*

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department")
@Where(clause = "is_deleted = 0")
class Department : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = 0

    @Column(name = "name")
    var name: String? = null

    @Column(name = "description")
    var description: String? = null
}