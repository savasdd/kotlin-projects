package com.kotlin.api.app

import com.kotlin.api.entity.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository : JpaRepository<Person, Long> {}