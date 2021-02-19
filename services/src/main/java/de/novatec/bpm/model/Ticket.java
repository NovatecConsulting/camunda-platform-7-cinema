package de.novatec.bpm.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ticket {

    private String code;
    private String info;

    public Ticket(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String user, String reservierung, String ticketId) {
        String vorstellung = new SimpleDateFormat("dd.MM.yy").format(new Date());
        String jetzt = new SimpleDateFormat("dd.MM.yy HH:mm:ss").format(new Date());
        this.info = String.format("Hallo %s, " +
                        "vielen Dank für deine Reservierung vom %s.\n" +
                        "Hier nochmal alle Daten im Überblick:\n" +
                        "Vorstellung: Not another BPM movie, %s 21:00 Uhr\n" +
                        "Reservierung: %s\n" +
                        "Ticket id: %s\n" +
                        "Viel Spaß beim Film\n\n" +
                        "Dein Camunda Kino Team!",
                user, jetzt, vorstellung, reservierung, ticketId);
    }
}
