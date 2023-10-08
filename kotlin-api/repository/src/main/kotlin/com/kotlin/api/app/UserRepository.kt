package com.kotlin.api.app

import com.kotlin.api.entity.Users
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<Users, Long> {
    fun findByUsername(username: String?): Optional<Users>
    fun existsByUsername(username: String?): Boolean
}