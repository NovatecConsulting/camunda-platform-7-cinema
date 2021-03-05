package de.novatec.bpm.config;

import de.novatec.bpm.delegate.*;
import de.novatec.bpm.service.*;
import org.flowable.engine.RuntimeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DelegateConfig {

    @Bean(name = "paymentDelegate")
    public PaymentDelegate paymentDelegate(PaymentService paymentService) {
        return new PaymentDelegate(paymentService);
    }

    @Bean(name = "seatsDelegate")
    public SeatsDelegate seatsDelegate(SeatService seatService) {
        return new SeatsDelegate(seatService);
    }

    @Bean(name = "ticketDelegate")
    public TicketDelegate ticketDelegate(TicketService ticketService, QRCodeService qrCodeService, RuntimeService runtimeService) {
        return new TicketDelegate(ticketService, qrCodeService, runtimeService);
    }

    @Bean(name = "userDelegate")
    public UserDelegate userDelegate(UserService userService) {
        return new UserDelegate(userService);
    }

    @Bean(name = "loggerDelegate")
    public LoggerDelegate loggerDelegate() {
        return new LoggerDelegate();
    }

}
