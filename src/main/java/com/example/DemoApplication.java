package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
	class Foo {}
	class Bar {}

	@Bean
	Bar bar() {
		return new Bar();
	}

	@Bean
	Foo foo(Bar bar) {
		return new Foo();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
