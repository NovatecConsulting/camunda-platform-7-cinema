package de.novatec.bpm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    Logger logger = LoggerFactory.getLogger(PaymentService.class);

    public void issueMoney(int ticketPrice, String iban, String bic) {
        logger.info("Getting {} Euro from IBAN {}, BIC {}", ticketPrice, iban, bic);
    }
}
