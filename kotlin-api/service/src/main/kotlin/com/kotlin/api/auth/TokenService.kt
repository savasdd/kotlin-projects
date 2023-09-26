package com.kotlin.api.auth

import com.kotlin.api.entity.Users

interface TokenService {
    fun generateToken(user: Users): String
    fun parseToken(token: String): Users
}