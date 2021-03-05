package de.novatec.bpm.delegate;

import de.novatec.bpm.variable.FlowableVariableHandler;
import de.novatec.bpm.exception.PaymentException;
import de.novatec.bpm.model.Reservation;
import de.novatec.bpm.model.UserAccount;
import de.novatec.bpm.service.PaymentService;
import org.flowable.engine.delegate.BpmnError;
import org.flowable.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentDelegate {

    private final Logger logger = LoggerFactory.getLogger(PaymentDelegate.class);
    private final PaymentService paymentService;

    public PaymentDelegate(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void issueMoney(DelegateExecution execution) {
        Reservation reservation = FlowableVariableHandler.getReservation(execution);
        UserAccount user = reservation.getUserAccount();
        try {
            paymentService.issueMoney(reservation.getPrice(), user.getIban(), user.getBic());
        } catch (PaymentException e) {
            throw new BpmnError("B002");
        }
        logger.info("Transaction successful");
    }

    public void giveMoneyBack(DelegateExecution execution) {
        Reservation reservation = FlowableVariableHandler.getReservation(execution);
        UserAccount user = reservation.getUserAccount();
        paymentService.giveMoneyBack(reservation.getPrice(), user.getIban(), user.getBic());
        logger.info("Transaction successful");
    }
}
