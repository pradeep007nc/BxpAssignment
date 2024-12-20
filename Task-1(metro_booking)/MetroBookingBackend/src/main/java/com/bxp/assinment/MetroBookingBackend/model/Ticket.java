package com.bxp.assinment.MetroBookingBackend.model;

import com.bxp.assinment.MetroBookingBackend.enums.ActiveStatus;
import com.bxp.assinment.MetroBookingBackend.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "ticket")
public class Ticket extends BaseEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @ToString.Exclude
    @JoinColumn(name = "from_station_id")
    private Station fromStation;

    @ManyToOne(fetch = FetchType.EAGER)
    @ToString.Exclude
    @JoinColumn(name = "to_station_id")
    private Station toStation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id")
    @ToString.Exclude
    private Passenger passenger;

    private TicketStatus ticketBookingStatus;
    private ActiveStatus ticketActiveStatus;
    private String ticketReferenceNumber;
    private Double ticketPrice;
}
