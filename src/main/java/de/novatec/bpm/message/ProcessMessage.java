package de.novatec.bpm.message;

public enum ProcessMessage {

    ISSUE_TICKETS("IssueTickets");

    private String name;

    ProcessMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


}
