package de.novatec.bpm.delegate;

import de.novatec.bpm.model.Reservation;
import de.novatec.bpm.service.SeatService;
import de.novatec.bpm.variable.VariableHandler;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public class SeatsDelegate {

    private final Logger logger = LoggerFactory.getLogger(SeatsDelegate.class);

    @Value("${server.port}")
    private int port;

    private final SeatService seatService;

    public SeatsDelegate(SeatService seatService) {
        this.seatService = seatService;
    }

    public void releaseSeats(DelegateExecution execution) {
        boolean useOriginalSeats = VariableHandler.getSeatsAreAvailable(execution);
        List<String> seats;
        if (useOriginalSeats) {
            seats = VariableHandler.getReservation(execution).getSeats();
        } else {
            seats = VariableHandler.getAltSeats(execution);
        }
        seatService.releaseSeats(seats);
    }

    public void setAltSeats(DelegateExecution execution) {
        List<String> seats = VariableHandler.getReservation(execution).getSeats();
        VariableHandler.setAltSeats(execution, seatService.getAlternativeSeats(seats));
    }

    public void reserveSeats(DelegateExecution execution) {
        boolean useOriginalSeats = VariableHandler.getSeatsAreAvailable(execution);
        Reservation reservation = VariableHandler.getReservation(execution);
        List<String> seats = getSeatsToReserve(execution, useOriginalSeats, reservation);
        reservation.setPrice(seatService.getTicketPrice(seats));
        seatService.reserveSeats(seats);
        reservation.setSeats(seats);
        VariableHandler.setReservation(execution, reservation);
    }

    public void checkSeatAvailabilty(DelegateExecution execution) {
        List<String> seats = VariableHandler.getReservation(execution).getSeats();
        VariableHandler.setSeatsAreAvailable(execution, seatService.seatsAvailable(seats));
    }

    public void offerAltSeats(DelegateExecution execution) {
        List<String> seats = VariableHandler.getAltSeats(execution);
        logger.info("The seats you selected are not available. Alternative seats are {}", seats);
        logger.info("To accept these seats, click the following link: http://localhost:{}/offer/{}", port, execution.getBusinessKey());
    }

    private List<String> getSeatsToReserve(DelegateExecution execution, boolean useOriginalSeats,
                                           Reservation reservation) {
        if (useOriginalSeats) {
            return reservation.getSeats();
        } else {
            return VariableHandler.getAltSeats(execution);
        }
    }

}
