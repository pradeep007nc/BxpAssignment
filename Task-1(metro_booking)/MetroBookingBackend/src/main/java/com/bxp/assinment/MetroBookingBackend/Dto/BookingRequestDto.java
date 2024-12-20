package com.bxp.assinment.MetroBookingBackend.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingRequestDto {

    //can pass any one of these fields
    @Email(message = "Invalid email format")
    private String passengerEmail;

    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Invalid phone number format. It should be a 10-digit number."
    )
    private String passengerMobile;

    //station details
    private Long fromStationNumber;
    private Long toStationNumber;
}
