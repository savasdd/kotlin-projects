package com.kotlin.api.auth.impl

import com.kotlin.api.app.UserService
import com.kotlin.api.auth.AuthService
import com.kotlin.api.auth.HashService
import com.kotlin.api.auth.TokenService
import com.kotlin.api.dto.TokenDto
import com.kotlin.api.dto.TokenResponseDto
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val hashService: HashService,
    private val tokenService: TokenService,
    private val userService: UserService,
) : AuthService {
    override fun getToken(dto: TokenDto): TokenResponseDto {
        val user = userService.findByUsername(dto.username) ?: throw Exception("Login failed")

        if (!hashService.checkBcrypt(dto.password, user.password))
            throw Exception("Login failed")

        return TokenResponseDto(token = tokenService.generateToken(user))
    }
}