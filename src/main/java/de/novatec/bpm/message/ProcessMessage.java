package de.novatec.bpm.message;

public enum ProcessMessage {

    ISSUE_TICKETS("IssueTickets");

    ProcessMessage(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return this.name;
    }


}
