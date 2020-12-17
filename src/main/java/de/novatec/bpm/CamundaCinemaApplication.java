package de.novatec.bpm;

import de.novatec.bpm.config.CamundaConfig;
import de.novatec.bpm.config.DelegateConfig;
import de.novatec.bpm.config.ServiceConfig;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableProcessApplication("camundaCinema")
@Import({CamundaConfig.class, DelegateConfig.class, ServiceConfig.class})
public class CamundaCinemaApplication {

    public static void main(final String... args) {
        SpringApplication.run(CamundaCinemaApplication.class, args);
    }
}
