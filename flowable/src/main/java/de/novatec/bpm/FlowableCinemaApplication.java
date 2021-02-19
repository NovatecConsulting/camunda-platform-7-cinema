package de.novatec.bpm;

import de.novatec.bpm.config.DelegateConfig;
import de.novatec.bpm.config.ServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({DelegateConfig.class, ServiceConfig.class})
public class FlowableCinemaApplication {

    public static void main(final String... args) {
        SpringApplication.run(FlowableCinemaApplication.class, args);
    }
}
