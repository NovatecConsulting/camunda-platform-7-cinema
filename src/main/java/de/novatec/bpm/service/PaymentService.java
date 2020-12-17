package de.novatec.bpm.service;

import de.novatec.bpm.exception.PaymentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void issueMoney(int ticketPrice, String iban, String bic) {
        if (Math.random() < 0.25) {
            throw new PaymentException("There was an issue with the payment");
        } else {
            logger.info("Getting {} Euro from IBAN {}, BIC {}", ticketPrice, iban, bic);
        }
    }

    public void giveMoneyBack(int ticketPrice, String iban, String bic) {
        logger.info("Sending {} Euro to IBAN {}, BIC {}", ticketPrice, iban, bic);
    }
}
