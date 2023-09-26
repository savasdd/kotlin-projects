package com.kotlin.api.auth.impl

import com.kotlin.api.app.UserService
import com.kotlin.api.auth.TokenService
import com.kotlin.api.entity.Users
import org.springframework.security.oauth2.jwt.*
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit

@Service
class TokenServiceImpl(
    private val jwtDecoder: JwtDecoder,
    private val jwtEncoder: JwtEncoder,
    private val userService: UserService

) : TokenService {

    private final var hash: String = "HS256";
    override fun generateToken(user: Users): Jwt {
        val header = JwsHeader.with { hash }.build()
        val claims = JwtClaimsSet.builder()
            .issuedAt(Instant.now()).expiresAt(Instant.now().plus(30L, ChronoUnit.DAYS))
            .subject(user.username).claim("userId", user.id).build()

        return jwtEncoder.encode(JwtEncoderParameters.from(header, claims))
    }

    override fun parseToken(token: String): Users {
        val jwt = jwtDecoder.decode(token)
        val userId = jwt.claims["userId"] as Long
        return userService.findById(userId)
    }
}