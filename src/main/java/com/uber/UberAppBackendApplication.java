package com.uber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UberAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(UberAppBackendApplication.class, args);
		System.out.println("--------------------Hello Uber App-------------------");
	}

}
