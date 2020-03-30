package de.novatec.bpm.delegate;

import de.novatec.bpm.service.SeatService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeatAvailabilityDelegate implements JavaDelegate {

    @Autowired
    private SeatService seatService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<String> seats = (List<String>) execution.getVariable("seats");
        execution.setVariable("seatsAvailable", seatService.seatsAvailable(seats));
    }
}
