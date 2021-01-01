package de.novatec.bpm.delegate;

import de.novatec.bpm.model.Reservation;
import de.novatec.bpm.model.UserAccount;
import de.novatec.bpm.service.UserService;
import de.novatec.bpm.variable.VariableHandler;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDelegate {

    private final Logger logger = LoggerFactory.getLogger(UserDelegate.class);

    private final UserService userService;

    public UserDelegate(UserService userService) {
        this.userService = userService;
    }

    public void loadUser(DelegateExecution execution) {
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
