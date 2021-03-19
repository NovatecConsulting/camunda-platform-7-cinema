package de.novatec.bpm.service;

import de.novatec.bpm.exception.PaymentException;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final boolean throwErrors;

    public PaymentService(boolean throwErrors) {
        this.throwErrors = throwErrors;
    }

    public void issueMoney(int ticketPrice, String iban, String bic) {
        if (throwErrors && Math.random() > 0.5) {
            logger.error("There was an issue with the payment");
            throw new PaymentException("Bank declined the transaction. Code: " + generateRandomBankReference());
        } else {
            logger.info("Getting {} Euro from IBAN {}, BIC {}", ticketPrice, iban, bic);
        }
    }

    private String generateRandomBankReference() {
        return RandomStringUtils.random(12, true, true).toUpperCase();
    }

    public void giveMoneyBack(int ticketPrice, String iban, String bic) {
        logger.info("Sending {} Euro to IBAN {}, BIC {}", ticketPrice, iban, bic);
    }
}
