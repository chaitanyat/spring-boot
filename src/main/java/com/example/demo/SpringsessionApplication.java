package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@EntityScan(
	basePackageClasses = { SpringsessionApplication.class, Jsr310JpaConverters.class }
)
@SpringBootApplication
public class SpringsessionApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringsessionApplication.class, args);
	}
}
