package de.novatec.bpm.delegate;

import de.novatec.bpm.model.Reservation;
import de.novatec.bpm.model.UserAccount;
import de.novatec.bpm.service.PaymentService;
import de.novatec.bpm.service.SeatService;
import de.novatec.bpm.variable.VariableHandler;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentDelegate implements JavaDelegate {

    Logger logger = LoggerFactory.getLogger(PaymentDelegate.class);

    @Autowired
    private SeatService seatService;

    @Autowired
    private PaymentService paymentService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Reservation reservation = VariableHandler.getReservation(execution);
        UserAccount user = reservation.getUserAccount();
        paymentService.issueMoney(reservation.getPrice(), user.getIban(), user.getBic());
        logger.info("Transaction successful");
    }
}
