package com.bxp.assinment.MetroBookingBackend.exception;

import lombok.Data;

@Data
public class TicketTimedOutException extends RuntimeException {
    private String message = "Ticket has been timed out as its been more than 18 hours after booking";
}
