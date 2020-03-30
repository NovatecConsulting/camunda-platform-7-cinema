package de.novatec.bpm.delegate;

import de.novatec.bpm.service.SeatService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReserveSeatsDelegate implements JavaDelegate {

    @Autowired
    private SeatService seatService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        boolean useOriginalSeats = (boolean) execution.getVariable("seatsAvailable");
        List<String> seats;
        if (useOriginalSeats) {
            seats = (List<String>) execution.getVariable("seats");
        } else {
            seats = (List<String>) execution.getVariable("alternativeSeats");
        }
        seatService.reserveSeats(seats);
    }
}
