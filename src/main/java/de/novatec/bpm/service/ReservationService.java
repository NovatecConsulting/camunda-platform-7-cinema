package de.novatec.bpm.service;

import de.novatec.bpm.model.Reservation;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReservationService {

    Map<String, Reservation> reservations;

    public ReservationService() {
        reservations = new HashMap<>();
    }

    public Reservation getReservationById(String id) {
        return reservations.getOrDefault(id, null);
    }

    public boolean reservationExists(String id) {
        return getReservationById(id) != null;
    }

}
