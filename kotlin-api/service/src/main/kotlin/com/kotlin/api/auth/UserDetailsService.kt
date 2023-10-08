package com.kotlin.api.auth

import com.kotlin.api.app.UserRepository
import com.kotlin.api.dto.UserSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserDetailsService(
    private val repository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = repository.findByUsername(username) ?: throw UsernameNotFoundException("$username not found")

        return UserSecurity(
            id = user.get().id,
            usernames = user.get().username,
            passwords = user.get().password,
            uAuthorities = Collections.singleton(SimpleGrantedAuthority("user"))
        )
    }
}