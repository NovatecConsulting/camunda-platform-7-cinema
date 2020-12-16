package de.novatec.bpm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService {

    Logger logger = LoggerFactory.getLogger(SeatService.class);

    public void reserveSeats(List<String> seats) {
        logSeatWithMessage(seats, "Seat reserved: {}");
    }

    public void releaseSeats(List<String> seats) {
        logSeatWithMessage(seats, "Seat released: {}");
    }

    public boolean seatsAvailable(List<String> seats) {
        return seats.stream().anyMatch(seat -> seatAvailable());
    }

    public List<String> getAlternativeSeats(List<String> seats) {
        List<String> alternativeSeats = new ArrayList<>();
        int index = 12;
        for (int i = 0; i < seats.size(); i++) {
            alternativeSeats.add("C" + index);
            index++;
        }
        return alternativeSeats;
    }

    public int getTicketPrice(List<String> seats) {
        return seats.size() * 9;
    }

    private void logSeatWithMessage(List<String> seats, String s) {
        seats.forEach(seat -> logger.info(s, seat));
    }

    private boolean seatAvailable() {
        return Math.random() > 0.15;  // in 15% of cases, seats are no longer available;
    }
}
