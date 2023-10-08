package com.kotlin.api.app.impl

import com.kotlin.api.app.UserRepository
import com.kotlin.api.app.UserService
import com.kotlin.api.dto.ResponseDto
import com.kotlin.api.dto.TokenDto
import com.kotlin.api.entity.Users
import com.kotlin.api.security.JwtTokenManager
import mu.KotlinLogging
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.webjars.NotFoundException

@Service
class UserServiceImpl(
    private val passwordEncoder: BCryptPasswordEncoder,
    private val repository: UserRepository,
    private val jwt: JwtTokenManager,
) : UserService {

    private val log = KotlinLogging.logger {}
    override fun getAll(): List<Users> {
        return repository.findAll()
    }

    override fun findById(id: Long): Users {
        return repository.findById(id).orElse(throw IllegalStateException("Not Found!"))
    }

    override fun create(dto: Users): Users {
        if (repository.existsByUsername(dto.username))
            throw Exception("User already found!")

        dto.password = passwordEncoder.encode(dto.password)
        var model = repository.save(dto)

        log.info("create user {}", dto.username)
        return model
    }

    override fun getToken(dto: TokenDto): ResponseDto {
        repository.findByUsername(dto.username).orElseThrow { NotFoundException("User Not Found!") }
        var token = jwt.generateToken(dto.username)
        var claims = jwt.getClaims(token)


        var response = ResponseDto(
            token = token,
            username = if (claims.containsKey("sub")) claims["sub"].toString() else null,
            expiredDate = if (claims.containsKey("exp")) claims["exp"] as Int? else null,
            role = if (claims.containsKey("role")) claims["role"].toString() else null
        );

        return response;
    }

}