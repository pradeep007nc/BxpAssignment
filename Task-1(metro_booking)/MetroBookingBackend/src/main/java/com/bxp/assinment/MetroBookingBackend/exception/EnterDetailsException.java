package com.bxp.assinment.MetroBookingBackend.exception;

import lombok.Data;

@Data
public class EnterDetailsException extends RuntimeException {
    private String message = "Please enter details";
}
