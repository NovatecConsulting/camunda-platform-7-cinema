package de.novatec.bpm;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.novatec.bpm.model.Reservation;
import de.novatec.bpm.variable.ProcessVariables;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.Execution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static de.novatec.bpm.message.ProcessMessage.TICKETS_VERIFIED;

@RestController
public class ReservationRestController {

    Logger logger = LoggerFactory.getLogger(ReservationRestController.class);

    @Autowired
    RuntimeService runtimeService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/reservation")
    public ResponseEntity<String> reserveSeat(@RequestBody Reservation reservation) {
        String reservationId = "RESERVATION-" + UUID.randomUUID().toString();
        reservation.setReservationId(reservationId);
        Map<String, Object> variables = new HashMap<>();
        variables.put(ProcessVariables.RESERVATION.getName(), objectMapper.valueToTree(reservation));
        runtimeService.startProcessInstanceByKey("ticket-reservation", reservationId, variables);
        return new ResponseEntity<>("Reservation issued: " + reservationId, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/reservation/{id}")
    public void cancelReservation(@PathVariable String id) {
        runtimeService.startProcessInstanceByKey("cancel-reservation", id);
    }

    @GetMapping("/offer/{id}")
    public ResponseEntity<String> acceptOffer(@PathVariable String id) {
        Execution currentExecution = findSubscribedExecutionIdByBusinessKey(id);
        if (currentExecution != null) {
            runtimeService.messageEventReceived(TICKETS_VERIFIED.getName(), currentExecution.getId());
            logger.error("The offer for reservation {} was accepted", id);
            return new ResponseEntity<>("Reservation change accepted", HttpStatus.OK);
        } else {
            logger.error("Reservation {} doesn't exist", id);
            return new ResponseEntity<>("Reservation doesn't exist", HttpStatus.NOT_FOUND);
        }
    }

    private Execution findSubscribedExecutionIdByBusinessKey(String id) {
        Execution parent = runtimeService.createExecutionQuery()
                .processInstanceBusinessKey(id)
                .singleResult();
        if (parent != null) {
            return runtimeService.createExecutionQuery()
                    .messageEventSubscriptionName(TICKETS_VERIFIED.getName())
                    .parentId(parent.getId())
                    .singleResult();
        }
        return null;
    }

}
