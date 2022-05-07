package com.history.tree

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.web.reactive.config.EnableWebFlux
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableWebFlux
@EnableSwagger2
@EntityScan(basePackages = ["com.history.tree.model"])

class TreeApplication

fun main(args: Array<String>) {
    SpringApplication.run(TreeApplication::class.java, *args)
}