package com.kotlin.api.dto

data class ResponseDto(
    val username: String?,
    val expiredDate: Int?,
    val token: String?,
    val role: String?
)
