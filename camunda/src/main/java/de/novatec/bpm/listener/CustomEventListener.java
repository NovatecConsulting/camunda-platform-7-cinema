package de.novatec.bpm.listener;

import de.novatec.bpm.variable.CamundaVariableHandler;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomEventListener implements ExecutionListener {

    private final Logger logger = LoggerFactory.getLogger(CustomEventListener.class);

    @Override
    public void notify(DelegateExecution execution) {
        if (execution.getEventName().equals(EVENTNAME_END)) {
            String currentActivityName = execution.getCurrentActivityName();
            if (currentActivityName.contains("failed")) {
                logger.info("End event with status failed was reached");
                CamundaVariableHandler.setReservationSuccess(execution, false);
            } else if(currentActivityName.contains("successful")) {
                logger.info("End event with status success was reached");
                CamundaVariableHandler.setReservationSuccess(execution, true);
            } else if (currentActivityName.contains("compensate")) {
                logger.info("compensation fired");
            }
        }
    }
}
