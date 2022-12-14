package com.sachin.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//test URL http://localhost:9999/swagger-ui.html
public class SpringDataJdbcTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJdbcTutorialApplication.class, args);
	}

}
