package de.novatec.bpm.delegate;

import de.novatec.bpm.model.Reservation;
import de.novatec.bpm.model.UserAccount;
import de.novatec.bpm.service.UserService;
import de.novatec.bpm.variable.VariableHandler;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDelegate implements JavaDelegate {

    Logger logger = LoggerFactory.getLogger(UserDelegate.class);

    @Autowired
    UserService userService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String user = VariableHandler.getReservation(execution).getUserId();
        try {
            UserAccount account = userService.getUserById(user);
            Reservation reservation = VariableHandler.getReservation(execution);
            reservation.setUserAccount(account);
            VariableHandler.setReservation(execution, reservation);
            logger.info("User {} exists", user);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equals("User unknown")) {
                logger.error("User {} is unknown", user);
                throw new BpmnError("B002", "User is unknown");
            } else {
                logger.error("Unknown error in UserService");
                throw e;
            }
        }
    }
}
