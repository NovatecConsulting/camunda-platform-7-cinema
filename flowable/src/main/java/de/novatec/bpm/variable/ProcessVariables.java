package de.novatec.bpm.variable;

public enum ProcessVariables {

    ALT_SEATS("alternativeSeat"),
    RESERVATION("reservation"),
    USER_ID("userId"),
    SEATS_AVAILABLE("seatsAvailable"),
    USER_DATA("userData"),
    PROCESS_SUCCESS("processSuccess"),
    TICKET("ticket"),
    QR("qr");

    private String name;

    ProcessVariables(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
