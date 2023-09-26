package com.kotlin.api.auth

interface HashService {
    fun checkBcrypt(input: String, hash: String): Boolean
    fun hashBcrypt(input: String?): String
}