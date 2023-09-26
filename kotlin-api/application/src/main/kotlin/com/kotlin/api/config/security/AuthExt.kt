package com.kotlin.api.config.security

import com.kotlin.api.entity.Users
import org.springframework.security.core.Authentication

class AuthExt {

    fun Authentication.toUser(): Users {
        return principal as Users
    }
}