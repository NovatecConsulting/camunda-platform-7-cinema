package de.novatec.bpm.service;

import de.novatec.bpm.model.Reservation;
import de.novatec.bpm.variable.ProcessVariables;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.MismatchingMessageCorrelationException;
import org.camunda.bpm.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class ReservationRestController {

    Logger logger = LoggerFactory.getLogger(ReservationRestController.class);

    @Autowired
    RuntimeService runtimeService;

    @PostMapping("/reservation")
    public ResponseEntity reserveSeat(@RequestBody Reservation reservation) {
        String reservationId = "RESERVATION-" + UUID.randomUUID().toString();
        reservation.setReservationId(reservationId);
        Map<String, Object> variables = new HashMap<>();
        variables.put(ProcessVariables.RESERVATION.getName(), reservation);
        runtimeService.startProcessInstanceByKey("ticket-reservation", reservationId, variables);
        return new ResponseEntity<>("Reservation issued: " + reservationId, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/reservation/{id}")
    public void cancelReservation(@PathVariable String id) {
        runtimeService.startProcessInstanceByKey("cancel-reservation", id);
    }

    @GetMapping("/offer/{id}")
    public ResponseEntity acceptOffer(@PathVariable String id) {
        try {
            runtimeService.correlateMessage("SeatsVerifiedByCustomer", id);
            logger.error("The offer for reservation {} was accepted", id);
            return new ResponseEntity<>("Reservation change accepted", HttpStatus.OK);
        } catch (MismatchingMessageCorrelationException e) {
            logger.error("The reservation {} does not exist", id);
            return new ResponseEntity<>("Reservation doesn't exist", HttpStatus.NOT_FOUND);
        }

    }

}
