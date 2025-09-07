package com.airesearch.springopenai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringopenaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringopenaiApplication.class, args);
		System.out.println("Spring boot & Open AI working together...");
	}

}
