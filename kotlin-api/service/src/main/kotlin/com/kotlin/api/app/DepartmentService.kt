package com.kotlin.api.app

import com.kotlin.api.entity.Department

interface DepartmentService {

    fun getAll(): List<Department>

    fun getById(id: Long): Department
    fun create(dto: Department): Department
    fun update(id: Long, dto: Department): Department
    fun delete(id: Long)
}