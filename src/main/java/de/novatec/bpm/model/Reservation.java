package de.novatec.bpm.model;

import java.util.List;
import java.util.UUID;

public class Reservation {

    private String reservationId;
    private List<String> seats;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Reservation() {
        this.reservationId = UUID.randomUUID().toString();
    }

    public String getReservationId() {
        return reservationId;
    }

    public List<String> getSeats() {
        return seats;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats;
    }
}
