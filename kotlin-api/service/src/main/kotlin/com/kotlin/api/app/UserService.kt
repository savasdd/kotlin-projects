package com.kotlin.api.app

import com.kotlin.api.dto.ResponseDto
import com.kotlin.api.dto.TokenDto
import com.kotlin.api.entity.Users

interface UserService {

    fun getAll(): List<Users>
    fun findById(id: Long): Users
    fun create(dto: Users): Users
    fun getToken(dto: TokenDto): ResponseDto

}