package de.novatec.bpm.service;

import de.novatec.bpm.model.Reservation;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketService {

    public String generateTicket(Reservation reservation) {
        return UUID.randomUUID().toString();
    }

}
