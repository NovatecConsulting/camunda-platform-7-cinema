package de.novatec.bpm.delegate;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerDelegate implements JavaDelegate {

    private final Logger logger = LoggerFactory.getLogger(LoggerDelegate.class);

    @Override
    public void execute(DelegateExecution execution) {
        logger.info("Hello, Flowable!");
    }
}
