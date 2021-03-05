package de.novatec.bpm.delegate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerDelegate {

    private final Logger logger = LoggerFactory.getLogger(LoggerDelegate.class);

    public void log(String message) {
        logger.info("Hello, Flowable!");
    }
}
