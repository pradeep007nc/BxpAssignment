package com.bxp.assinment.MetroBookingBackend.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StationResponseDto {
    private String stationName;
    private Double price;
    private Long stationNumber;
}
