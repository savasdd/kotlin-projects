package com.kotlin.api.web

import com.kotlin.api.app.DepartmentService
import com.kotlin.api.entity.Department
import io.swagger.v3.oas.annotations.Operation
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
class DepartmentController(
    @Autowired
    val service: DepartmentService
) {

    @Operation(description = "Department getAll")
    @GetMapping(value = ["/departments"])
    fun getAllDepartment(): ResponseEntity<List<Department>> {
        return ResponseEntity(service.getAll(), HttpStatus.OK)
    }

    @Operation(description = "Department getById")
    @GetMapping(value = ["/departments/{id}"])
    fun getByIdDepartment(@PathVariable id: Long): ResponseEntity<Department> {
        return ResponseEntity(service.getById(id), HttpStatus.OK)
    }

    @Operation(description = "Department create")
    @PostMapping(value = ["/departments"])
    fun createDepartment(@RequestBody dt: Department): ResponseEntity<Department> {
        return ResponseEntity(service.create(dt), HttpStatus.CREATED)
    }

    @Operation(description = "Department update")
    @PutMapping(value = ["/departments/{id}"])
    fun updateDepartment(@PathVariable id: Long, @RequestBody dto: Department): ResponseEntity<Department> {
        return ResponseEntity(service.update(id, dto), HttpStatus.OK)
    }

    @Operation(description = "Department delete")
    @DeleteMapping(value = ["/departments/{id}"])
    fun deleteDepartment(@PathVariable id: Long): ResponseEntity<Department> {
        service.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT);
    }
}