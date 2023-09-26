package com.kotlin.api.dto

import java.time.Instant

data class TokenResponseDto(
    val username: String,
    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    val expiredDate: Instant,
    val token: String,
)
