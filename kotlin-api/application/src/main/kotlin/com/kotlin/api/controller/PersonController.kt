package com.kotlin.api.controller

import ch.qos.logback.core.pattern.util.RegularEscapeUtil
import com.kotlin.api.app.PersonService
import com.kotlin.api.entity.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
@CrossOrigin(allowedHeaders = ["*"], origins = ["*"])
class PersonController(
    @Autowired
    val service: PersonService
) {

    @GetMapping(value = ["/persons"])
    fun getAll(): ResponseEntity<List<Person>> {
        return ResponseEntity.ok(service.getAll())
    }

    @PostMapping(value = ["/persons"])
    fun create(@RequestBody dto: Person): ResponseEntity<Person> {
        return ResponseEntity(service.create(dto), HttpStatus.CREATED)
    }

    @PutMapping(value = ["/persons/{id}"])
    fun update(@PathVariable id: Long, @RequestBody dto: Person): ResponseEntity<Person> {
        return ResponseEntity(service.update(id, dto), HttpStatus.OK)
    }

    @DeleteMapping(value = ["/persons/{id}"])
    fun delete(@PathVariable id: Long): ResponseEntity<Person> {
        service.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }


}