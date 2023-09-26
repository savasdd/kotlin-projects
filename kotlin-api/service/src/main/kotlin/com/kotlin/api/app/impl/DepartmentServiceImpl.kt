package com.kotlin.api.app.impl

import com.kotlin.api.app.DepartmentRepository
import com.kotlin.api.app.DepartmentService
import com.kotlin.api.entity.Department
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DepartmentServiceImpl(
    @Autowired
    val repository: DepartmentRepository

) : DepartmentService {

    private val log = KotlinLogging.logger {}
    override fun getAll(): List<Department> {
        var list = repository.findAll();

        return list
    }

    override fun getById(id: Long): Department {
        return repository.findById(id).orElse(throw IllegalStateException("Not Found!"))
    }

    override fun create(dto: Department): Department {
        var model = repository.save(dto)

        log.info("create Department")
        return model
    }

    override fun update(id: Long, dto: Department): Department {
        val debs = repository.findById(id)
        var update = debs.stream().map { m ->
            m.name = dto?.name
            m.description = dto?.description
            return@map m;
        }.findFirst().get()

        var model = repository.save(update)
        log.info("update Department ", id)
        return model
    }

    override fun delete(id: Long) {
        if (repository.existsById(id))
            repository.deleteById(id)
    }
}