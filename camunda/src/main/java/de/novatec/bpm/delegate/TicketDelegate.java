package de.novatec.bpm.delegate;

import com.google.zxing.WriterException;
import de.novatec.bpm.message.ProcessMessage;
import de.novatec.bpm.model.Reservation;
import de.novatec.bpm.model.Ticket;
import de.novatec.bpm.model.UserAccount;
import de.novatec.bpm.service.QRCodeService;
import de.novatec.bpm.service.TicketService;
import de.novatec.bpm.variable.VariableHandler;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class TicketDelegate {

    private final Logger logger = LoggerFactory.getLogger(TicketDelegate.class);
    private final TicketService ticketService;
    private final QRCodeService qrCodeService;
    private final RuntimeService runtimeService;

    public TicketDelegate(TicketService ticketService, QRCodeService qrCodeService, RuntimeService runtimeService) {
        this.ticketService = ticketService;
        this.qrCodeService = qrCodeService;
        this.runtimeService = runtimeService;
    }

    public void generateTickets(DelegateExecution execution) throws IOException, WriterException {
        Reservation reservation = VariableHandler.getReservation(execution);
        Ticket ticket = ticketService.generateTickets(reservation);
        File qrCode = qrCodeService.generateQRCode(ticket.getCode());
        VariableHandler.setTicket(execution, ticket);
        VariableHandler.setQRCode(execution, qrCode);
        logger.info("Ticket {} generated", ticket.getCode());
    }

    public void sendTickets(DelegateExecution execution) {
        UserAccount user = VariableHandler.getReservation(execution).getUserAccount();
        Ticket ticket = VariableHandler.getTicket(execution);
        logger.info("Sending tickets to {}", user.getEmail());
        logger.info(ticket.getInfo());
    }

    public void triggerTicketProcess(DelegateExecution execution) {
        Reservation reservation = VariableHandler.getReservation(execution);
        runtimeService.startProcessInstanceByMessage(ProcessMessage.ISSUE_TICKETS.getName(),
                execution.getBusinessKey(), execution.getVariables());
        logger.info("Tickets for reservation {} are going to be generated",
                reservation.getReservationId());
    }
}
