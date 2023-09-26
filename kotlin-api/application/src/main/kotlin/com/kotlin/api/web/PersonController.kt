package com.kotlin.api.web

import com.kotlin.api.app.PersonService
import com.kotlin.api.entity.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

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