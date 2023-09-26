package com.kotlin.api.web

import com.kotlin.api.auth.AuthService
import com.kotlin.api.dto.TokenDto
import com.kotlin.api.dto.TokenResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
@CrossOrigin(allowedHeaders = ["*"], origins = ["*"])
class AuthController(
    private val service: AuthService
) {

    @PostMapping(value = ["/auth/getToken"])
    fun createPerson(@RequestBody dto: TokenDto): ResponseEntity<TokenResponseDto> {
        return ResponseEntity(service.getToken(dto), HttpStatus.OK)
    }


}