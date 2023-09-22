package com.kotlin.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinPersonApiApplication

fun main(args: Array<String>) {
    runApplication<KotlinPersonApiApplication>(*args)
}
