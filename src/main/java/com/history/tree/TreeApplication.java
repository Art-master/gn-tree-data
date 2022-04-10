package com.history.tree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
@EntityScan(basePackages = "com.history.tree.model")
public class TreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TreeApplication.class, args);
	}

}
