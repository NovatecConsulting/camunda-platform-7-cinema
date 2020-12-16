package de.novatec.bpm;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableProcessApplication("camundaCinema")
@Import(ApplicationConfig.class)
public class CamundaCinemaApplication {

    public static void main(final String... args) {
        SpringApplication.run(CamundaCinemaApplication.class, args);
    }
}
