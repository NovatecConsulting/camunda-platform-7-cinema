package de.novatec.bpm;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication("camundaCinema")
public class CamundaCinemaApplication {
    public static void main(final String... args) {
        SpringApplication.run(CamundaCinemaApplication.class, args);
    }
}
