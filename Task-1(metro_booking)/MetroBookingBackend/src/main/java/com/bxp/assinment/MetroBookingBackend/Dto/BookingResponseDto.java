package com.bxp.assinment.MetroBookingBackend.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponseDto {
    private String ticketReferenceNumber;
    private Double price;
    private String message;
}
