package com.kotlin.api.app

import com.kotlin.api.entity.Person

interface PersonService {

    fun getAll(): List<Person>
    fun create(dto: Person): Person
    fun update(id: Long, dto: Person): Person
    fun delete(id: Long)
}