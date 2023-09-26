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
@Table(name = "person")
@Where(clause = "is_deleted = 0")
class Person : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dempartment")
    var dempartment: Department? = null;

    @Column(name = "firstName")
    var firstName: String? = null

    @Column(name = "lastName")
    var lastName: String? = null

    @Column(name = "age")
    var age: Int? = null

    @Column(name = "salary")
    var salary: Double? = null
}
