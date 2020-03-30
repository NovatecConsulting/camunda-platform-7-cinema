package de.novatec.bpm.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OfferSeatsDelegate implements JavaDelegate {

    Logger logger = LoggerFactory.getLogger(OfferSeatsDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<String> seats = (List<String>)execution.getVariable("alternativeSeats");
        logger.info("The seats you selected are not available. Alternative seats are {}", seats);
        logger.info("To accept these seats, click the following link: http://localhost:8080/offer/{}", execution.getBusinessKey());
    }
}
