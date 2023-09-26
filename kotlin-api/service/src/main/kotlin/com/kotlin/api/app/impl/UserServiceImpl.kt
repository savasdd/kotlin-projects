package com.kotlin.api.app.impl

import com.kotlin.api.app.UserRepository
import com.kotlin.api.app.UserService
import com.kotlin.api.auth.HashService
import com.kotlin.api.entity.Users
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val hashService: HashService,
    private val repository: UserRepository
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
        dto.password = hashService.hashBcrypt(dto.password)

        log.info("create user {}", dto.username)
        return repository.save(dto)
    }

    override fun findByUsername(username: String): Users {
        return repository.findByUsername(username).get()
    }

    override fun existByUsername(username: String): Boolean {
        return repository.existsByUsername(username)
    }
}