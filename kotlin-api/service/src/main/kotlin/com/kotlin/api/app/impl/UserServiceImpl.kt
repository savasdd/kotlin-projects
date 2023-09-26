package com.kotlin.api.app.impl

import com.kotlin.api.app.UserRepository
import com.kotlin.api.app.UserService
import com.kotlin.api.entity.Users
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    @Autowired
    val repository: UserRepository
) : UserService {
    override fun getAll(): List<Users> {
        return repository.findAll()
    }

    override fun findById(id: Long): Users {
        return repository.findById(id).orElse(throw IllegalStateException("Not Found!"))
    }

    override fun create(dto: Users): Users {
        return repository.save(dto)
    }

    override fun findByUsername(username: String): Users {
        return repository.findByUsername(username).orElse(throw IllegalStateException("User Not Found!"))
    }

    override fun existByUsername(username: String): Boolean {
        return repository.existsByUsername(username)
    }
}