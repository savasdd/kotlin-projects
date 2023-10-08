package com.kotlin.api.dto

data class ResponseDto(
    val token: String?,
    val username: String?,
    val expiredDate: Int?,
    val role: String?
)
