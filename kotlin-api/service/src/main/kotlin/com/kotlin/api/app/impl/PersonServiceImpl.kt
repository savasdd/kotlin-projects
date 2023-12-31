package com.kotlin.api.app.impl

import com.kotlin.api.app.DepartmentRepository
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
    val repository: PersonRepository,

    @Autowired
    val department: DepartmentRepository

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
        val persons = repository.findById(id);
        val dep = dto.dempartment?.id?.let { department.findById(it) }

        var update = persons.stream().map { m ->
            m.firstName = dto?.firstName
            m.lastName = dto?.lastName
            m.age = dto?.age
            m.salary = dto?.salary
            m.dempartment = dep?.get()
            return@map m
        }.findFirst().get();

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