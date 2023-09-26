package com.kotlin.api.app

import com.kotlin.api.entity.Users

interface UserService {

    fun getAll(): List<Users>
    fun findById(id: Long): Users
    fun create(dto: Users): Users

    fun findByUsername(username: String): Users
    fun existByUsername(username: String): Boolean

}