package de.novatec.bpm.service;

import de.novatec.bpm.model.Reservation;
import de.novatec.bpm.model.Ticket;

import java.util.UUID;

public class TicketService {

    public Ticket generateTickets(Reservation reservation) {
        Ticket ticket = new Ticket(UUID.randomUUID().toString());
        ticket.setInfo(reservation.getUserId(), reservation.toString(), ticket.getCode());
        return ticket;
    }

}
