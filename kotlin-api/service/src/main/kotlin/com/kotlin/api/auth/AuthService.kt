package com.kotlin.api.auth

import com.kotlin.api.dto.TokenDto
import com.kotlin.api.dto.TokenResponseDto

interface AuthService {
    fun getToken(dto: TokenDto): TokenResponseDto
}