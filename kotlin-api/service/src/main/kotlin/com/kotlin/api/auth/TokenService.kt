package com.kotlin.api.auth

import com.kotlin.api.entity.Users
import org.springframework.security.oauth2.jwt.Jwt

interface TokenService {
    fun generateToken(user: Users): Jwt
    fun parseToken(token: String): Users
}