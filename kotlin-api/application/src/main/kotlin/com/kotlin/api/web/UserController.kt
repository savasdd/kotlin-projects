package com.kotlin.api.web

import com.kotlin.api.app.UserService
import com.kotlin.api.entity.Users
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
@CrossOrigin(allowedHeaders = ["*"], origins = ["*"])
class UserController(
    @Autowired
    val service: UserService
) {

    @GetMapping(value = ["/users"])
    fun getAllUsers(): ResponseEntity<List<Users>> {
        return ResponseEntity.ok(service.getAll())
    }

    @GetMapping(value = ["/users/{id}"])
    fun getByIdUsers(@PathVariable id: Long): ResponseEntity<Users> {
        return ResponseEntity.ok(service.findById(id))
    }

    @PostMapping(value = ["/users"])
    fun createPerson(@RequestBody dto: Users): ResponseEntity<Users> {
        return ResponseEntity(service.create(dto), HttpStatus.CREATED)
    }


}