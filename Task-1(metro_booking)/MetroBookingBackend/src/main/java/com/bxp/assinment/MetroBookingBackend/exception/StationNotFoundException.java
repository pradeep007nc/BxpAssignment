package com.bxp.assinment.MetroBookingBackend.exception;

import lombok.Data;

@Data
public class StationNotFoundException extends RuntimeException {
    private String message = "Station not found with the entered station number";
}
