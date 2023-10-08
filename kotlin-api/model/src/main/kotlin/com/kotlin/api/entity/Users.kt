package com.kotlin.api.entity

import com.kotlin.api.base.BaseEntity
import com.kotlin.api.enums.EUserRole
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
@Table(name = "users")
@Where(clause = "is_deleted = 0")
class Users : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "firstName")
    var firstName: String? = null

    @Column(name = "lastName")
    var lastName: String? = null

    @Column(name = "username")
    var username: String? = null

    @Column(name = "password")
    var password: String? = null

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    var role: EUserRole? = null
}
