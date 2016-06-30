package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Collection;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    @Bean
    CommandLineRunner runner(ReservationRepository rr) {
        return args -> {
            Arrays.asList("Alex,Bob,Bill,Jack,Samon,Rob".split(","))
                    .forEach(n->rr.save(new Reservation(n)));

            rr.findAll().forEach(System.out::println);
            System.out.println("Search Bob ...");
            rr.findByName("Bill").forEach(System.out::println);
            System.out.println("Search all users ending with 'ob' ...");
            rr.findByNameEndingWith("ob").forEach(System.out::println);
        };
    }
}

interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // select * from reservations where name = :rn
    Collection<Reservation> findByName(String rn);
    Collection<Reservation> findByNameEndingWith(String endings);
}

@RestController
class ReservationRestController {
    @RequestMapping("/reservations")
    Collection<Reservation> reservations() {
        return reservationRepository.findAll();
    }

    @Autowired
    private ReservationRepository reservationRepository;
}

@Entity
class Reservation {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Reservation() {     // why JPA, Why?
    }

    Reservation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
