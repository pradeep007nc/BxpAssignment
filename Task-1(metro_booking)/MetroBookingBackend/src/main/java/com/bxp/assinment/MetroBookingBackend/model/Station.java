package com.bxp.assinment.MetroBookingBackend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "station")
public class Station extends BaseEntity{
    private Long stationNumber;
    private String stationName;
    private Boolean isStartStation;
    private Boolean isEndStation;
    private Double price;
}
