package com.bxp.assinment.MetroBookingBackend.exception;

import lombok.Data;

@Data
public class TicketException extends RuntimeException {
    private String message = "Ticket has been expired or not found with the ticket reference number";
}
