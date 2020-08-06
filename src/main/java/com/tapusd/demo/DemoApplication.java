package com.tapusd.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("hello")
public class DemoApplication {

	@GetMapping("/{firstName}/{lastName}")
	public String hello(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
		return String.format("Hello , %s %s", firstName, lastName);

	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
