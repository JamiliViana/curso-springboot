package com.acme.tuor

import com.acme.tuor.model.Promocao
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class TuorApplication

fun main(args: Array<String>) {
	runApplication<TuorApplication>(*args)
}
