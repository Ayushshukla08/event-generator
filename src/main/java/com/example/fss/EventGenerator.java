package com.example.fss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.fss")
public class EventGenerator {

	public static void main(String[] args) {
		SpringApplication.run(EventGenerator.class, args);
	}

}
