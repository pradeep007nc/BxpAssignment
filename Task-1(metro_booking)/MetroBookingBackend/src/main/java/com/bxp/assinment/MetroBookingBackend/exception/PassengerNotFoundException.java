package com.bxp.assinment.MetroBookingBackend.exception;

import lombok.Data;

@Data
public class PassengerNotFoundException extends RuntimeException {
    private String message = "Passenger not found with the entered details";
}
