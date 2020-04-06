package de.novatec.bpm.delegate;

import de.novatec.bpm.model.Reservation;
import de.novatec.bpm.model.UserAccount;
import de.novatec.bpm.service.PaymentService;
import de.novatec.bpm.variable.VariableHandler;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentDelegate {

    Logger logger = LoggerFactory.getLogger(PaymentDelegate.class);

    @Autowired
    private PaymentService paymentService;

    public void issueMoney(DelegateExecution execution) {
        Reservation reservation = VariableHandler.getReservation(execution);
        UserAccount user = reservation.getUserAccount();
        paymentService.issueMoney(reservation.getPrice(), user.getIban(), user.getBic());
        logger.info("Transaction successful");
    }

    public void giveMoneyBack(DelegateExecution execution) {
        Reservation reservation = VariableHandler.getReservation(execution);
        UserAccount user = reservation.getUserAccount();
        paymentService.giveMoneyBack(reservation.getPrice(), user.getIban(), user.getBic());
        logger.info("Transaction successful");
    }
}
