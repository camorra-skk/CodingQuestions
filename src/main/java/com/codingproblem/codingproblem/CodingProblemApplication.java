package com.codingproblem.codingproblem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.codingproblem.codingproblem.DemoSpringRest"})
public class CodingProblemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodingProblemApplication.class, args);
		System.out.println("Working");
	}

}
