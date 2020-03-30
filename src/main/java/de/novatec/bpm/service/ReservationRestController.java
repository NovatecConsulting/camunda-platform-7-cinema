package de.novatec.bpm.service;

import de.novatec.bpm.model.Reservation;
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

@RestController
public class ReservationRestController {

    Logger logger = LoggerFactory.getLogger(ReservationRestController.class);

    @Autowired
    RuntimeService runtimeService;

    @GetMapping("/reservation/{id}")
    @ResponseBody
    public Reservation getReservation(@RequestParam String id) {
        return new Reservation();
    }

    @PostMapping("/reservation")
    public ResponseEntity reserveSeat(@RequestBody Reservation reservation) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("seats", reservation.getSeats());
        variables.put("userId", reservation.getUserId());
        runtimeService.startProcessInstanceByKey("ticket-reservation", reservation.getReservationId(), variables);
        return new ResponseEntity<>("Reservation issued", HttpStatus.OK);
    }

    @DeleteMapping("/reservation/{id}")
    public void cancelReservation(@PathVariable String id) {
        runtimeService.startProcessInstanceByKey("cancel-reservation", id);
    }

    @GetMapping("/offer/{id}")
    public ResponseEntity acceptOffer(@PathVariable String id) {
        try {
            runtimeService.correlateMessage("SeatsVerifiedByCustomer", id);
            logger.error("The offer for reservation {} was accepeted", id);
            return new ResponseEntity<>("Reservation change accepted", HttpStatus.OK);
        } catch(MismatchingMessageCorrelationException e) {
            logger.error("The reservation {} does not exist", id);
            return new ResponseEntity<>("Reservation doesn't exist", HttpStatus.NOT_FOUND);
        }

    }

}
