package com.kotlin.api.security

import com.kotlin.api.enums.EUserRole
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*

@Component
class JwtTokenManager(
    //@Value("\${security.key}")
    //private val secret: String
) {

    private val secret = "5cf249320242ac120002e5efebf0e985d220ecbb6676f00341be0a2b257e"
    private val expiration = 6000000
    private var test = Instant.now().plus(30L, ChronoUnit.DAYS)

    fun generateToken(username: String?): String {
        return Jwts
            .builder()
            //.setClaims(extraClaims)
            .claim("role", EUserRole.ADMIN.name)
            .setSubject(username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray()).compact()
    }

    fun getClaims(token: String) = Jwts.parser().setSigningKey(secret.toByteArray()).parseClaimsJws(token).body

    fun getEmail(token: String): String = getClaims(token).subject

    fun isTokenValid(token: String): Boolean {
        val claims = getClaims(token)
        val expirationDate = claims.expiration
        val now = Date(System.currentTimeMillis())
        return now.before(expirationDate)
    }
}