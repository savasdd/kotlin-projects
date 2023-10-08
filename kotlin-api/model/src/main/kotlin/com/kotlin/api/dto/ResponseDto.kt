package com.kotlin.api.dto

import java.time.Instant

data class ResponseDto(
    val username: String,
    val expiredDate: Instant,
    val token: String,
)
