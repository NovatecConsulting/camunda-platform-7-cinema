package de.novatec.bpm.service;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    Logger logger = LoggerFactory.getLogger(PaymentService.class);

    public void issueMoney(int ticketPrice, String iban, String bic) {
        if(Math.random() < 0.25) {
            logger.error("There was an issue with the payment");
            throw new BpmnError("B002");
        } else {
            logger.info("Getting {} Euro from IBAN {}, BIC {}", ticketPrice, iban, bic);
        }
    }

    public void giveMoneyBack(int ticketPrice, String iban, String bic) {
        logger.info("Sending {} Euro to IBAN {}, BIC {}", ticketPrice, iban, bic);
    }
}
