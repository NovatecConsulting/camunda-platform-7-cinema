package de.novatec.bpm.config;

import de.novatec.bpm.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Value("${cinema.delegate.payment.error:false}")
    public boolean throwErrors;

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(throwErrors);
    }

    @Bean
    public SeatService seatService() {
        return new SeatService();
    }

    @Bean
    public TicketService ticketService() {
        return new TicketService();
    }

    @Bean
    public QRCodeService qrCodeService() {
        return new QRCodeService();
    }

    @Bean
    public UserService userService() {
        return new UserService();
    }

}
