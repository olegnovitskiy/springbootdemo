package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

@Entity
class Reservations {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Reservations() {     // why JPA, Why?
    }

    Reservations(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Reservations{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
