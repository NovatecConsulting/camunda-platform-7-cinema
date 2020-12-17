package de.novatec.bpm.config;

import de.novatec.bpm.delegate.PaymentDelegate;
import de.novatec.bpm.delegate.SeatsDelegate;
import de.novatec.bpm.delegate.TicketDelegate;
import de.novatec.bpm.delegate.UserDelegate;
import de.novatec.bpm.service.*;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DelegateConfig {

    @Bean
    public PaymentDelegate paymentDelegate(PaymentService paymentService) {
        return new PaymentDelegate(paymentService);
    }

    @Bean
    public SeatsDelegate seatsDelegate(SeatService seatService) {
        return new SeatsDelegate(seatService);
    }

    @Bean
    public TicketDelegate ticketDelegate(TicketService ticketService, QRCodeService qrCodeService, RuntimeService runtimeService) {
        return new TicketDelegate(ticketService, qrCodeService, runtimeService);
    }

    @Bean
    public UserDelegate userDelegate(UserService userService) {
        return new UserDelegate(userService);
    }

}
