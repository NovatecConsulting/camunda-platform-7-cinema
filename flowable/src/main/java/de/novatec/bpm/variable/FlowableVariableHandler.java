package de.novatec.bpm.variable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.novatec.bpm.model.Reservation;
import de.novatec.bpm.model.Ticket;
import org.flowable.engine.delegate.DelegateExecution;

import java.util.List;

public class FlowableVariableHandler {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void setReservationSuccess(DelegateExecution execution, boolean value) {
        execution.setVariable(ProcessVariables.PROCESS_SUCCESS.getName(), value);
    }

    public static void setSeatsAreAvailable(DelegateExecution execution, boolean value) {
        execution.setVariable(ProcessVariables.SEATS_AVAILABLE.getName(), value);
    }

    public static boolean getSeatsAreAvailable(DelegateExecution execution) {
        Boolean value = (Boolean) execution.getVariable(ProcessVariables.SEATS_AVAILABLE.getName());
        checkIfSet(value, ProcessVariables.SEATS_AVAILABLE);
        return value;
    }

    public static void setReservation(DelegateExecution execution, Reservation reservation) {
        execution.setVariable(ProcessVariables.RESERVATION.getName(), objectMapper.valueToTree(reservation));
    }

    public static Reservation getReservation(DelegateExecution execution) {
        JsonNode variable = (JsonNode) execution.getVariable(ProcessVariables.RESERVATION.getName());
        Reservation reservation = null;
        try {
            reservation = objectMapper.treeToValue(variable, Reservation.class);
            checkIfSet(reservation, ProcessVariables.RESERVATION);
            return reservation;
        } catch (JsonProcessingException e) {
            return reservation;
        }
    }

    public static List<String> getAltSeats(DelegateExecution execution) {
        List<String> value = (List<String>) execution.getVariable(ProcessVariables.ALT_SEATS.getName());
        checkIfSet(value, ProcessVariables.ALT_SEATS);
        return value;
    }

    public static void setAltSeats(DelegateExecution execution, List<String> seats) {
        execution.setVariable(ProcessVariables.ALT_SEATS.getName(), seats);
    }

    public static void setTicket(DelegateExecution execution, Ticket ticket) {
        execution.setVariable(ProcessVariables.TICKET.getName(), objectMapper.valueToTree(ticket));
    }

    public static Ticket getTicket(DelegateExecution execution) {
        JsonNode variable = (JsonNode) execution.getVariable(ProcessVariables.RESERVATION.getName());
        Ticket value = null;
        try {
            value = objectMapper.treeToValue(variable, Ticket.class);
            checkIfSet(value, ProcessVariables.TICKET);
            return value;
        } catch (JsonProcessingException e) {
            return value;
        }
    }

//    public static void setQRCode(DelegateExecution execution, File qrCode) {
//        FileValue fileValue = Variables.fileValue(qrCode.getName()).file(qrCode).mimeType("image/png")
//                .encoding("UTF-8").create();
//        execution.setVariable(ProcessVariables.QR.getName(), fileValue);
//    }
//
//    public static File getQRCode(DelegateExecution execution) {
//        TypedValue variableTyped = execution.getVariableTyped(ProcessVariables.QR.getName());
//        if (variableTyped.getType().equals(ValueType.FILE)) {
//            return (File) variableTyped.getValue();
//        } else {
//            throw new IllegalArgumentException(
//                    String.format("Variable %s is not typed as file", ProcessVariables.QR.getName()));
//        }
//    }

    private static void checkIfSet(Object value, ProcessVariables variable) {
        if (value == null) {
            throw new IllegalArgumentException(
                    String.format("Variable %s is not set", variable.getName()));
        }
    }
}
