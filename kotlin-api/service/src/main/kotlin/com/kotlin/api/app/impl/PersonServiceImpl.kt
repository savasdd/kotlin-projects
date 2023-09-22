package com.kotlin.api.app.impl

import com.kotlin.api.app.PersonRepository
import com.kotlin.api.app.PersonService
import com.kotlin.api.entity.Person
import lombok.extern.slf4j.Slf4j
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Slf4j
@Service
class PersonServiceImpl(
    @Autowired
    val repository: PersonRepository


) : PersonService {

    private val log = KotlinLogging.logger {}

    override fun getAll(): List<Person> {
        var list = repository.findAll()

        log.info("getAll person {}", list.size)
        return list
    }

    override fun create(dto: Person): Person {
        var model = repository.save(dto)

        log.info("create person")
        return model
    }

    override fun update(id: Long, dto: Person): Person {
        val persons = repository.findById(id).orElse(null);
        var update = persons.copy(name = dto.name, surname = dto.surname, age = dto.age)

        var model = repository.save(update);
        log.info("update person {}", id)
        return model;
    }

    override fun delete(id: Long) {
        if (repository.existsById(id)) {
            repository.deleteById(id)
            log.info("delete person {}", id)
        }

    }
}