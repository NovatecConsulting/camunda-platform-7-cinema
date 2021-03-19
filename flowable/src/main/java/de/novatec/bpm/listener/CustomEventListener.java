package de.novatec.bpm.listener;

import de.novatec.bpm.variable.FlowableVariableHandler;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomEventListener implements ExecutionListener {

    private final Logger logger = LoggerFactory.getLogger(CustomEventListener.class);

    @Override
    public void notify(DelegateExecution execution) {
        if (execution.getEventName().equals(EVENTNAME_END)) {
            String name = execution.getCurrentFlowElement().getName();
            if (name.contains("failed")) {
                logger.info("end event with status 'failed' was reached");
                FlowableVariableHandler.setReservationSuccess(execution, false);
            } else if (name.contains("done")) {
                logger.info("end event with status 'success' was reached");
                FlowableVariableHandler.setReservationSuccess(execution, true);
            } else if (name.contains("compensate")) {
                logger.info("compensation fired");
            }
        }
    }
}
