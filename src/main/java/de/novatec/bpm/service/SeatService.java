package de.novatec.bpm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SeatService {

    private final Logger logger = LoggerFactory.getLogger(SeatService.class);
    private static final int MAX_SEATS_IN_ROW = 20;
    private static final int MAX_ROWS = 26;

    public void reserveSeats(List<String> seats) {
        logSeatWithMessage(seats, "Seat reserved: {}");
    }

    public void releaseSeats(List<String> seats) {
        logSeatWithMessage(seats, "Seat released: {}");
    }

    public boolean seatsAvailable(List<String> seats) {
        return seats.stream().allMatch(seat -> seatAvailable());
    }

    public List<String> getAlternativeSeats(List<String> seats) {
        List<String> alternativeSeats = new ArrayList<>();
        int seat = getRandomStartingSeat(seats.size());
        String row = getRandomRow();
        for (int i = 0; i < seats.size(); i++) {
            alternativeSeats.add(row + seat);
            seat++;
        }
        return alternativeSeats;
    }

    private int getRandomStartingSeat(int buffer) {
        return Math.max(1, new Random().nextInt(MAX_SEATS_IN_ROW - buffer));
    }

    private String getRandomRow() {
        char randomChar = (char) ('a' + new Random().nextInt(MAX_ROWS));
        return String.valueOf(randomChar).toUpperCase();
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
