package com.bxp.assinment.MetroBookingBackend.dao;

import com.bxp.assinment.MetroBookingBackend.model.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TicketDao extends CrudRepository<Ticket, Long> {
    Optional<Ticket> findByTicketReferenceNumber(String ticketReferenceNumber);

    @Query(value = "SELECT * FROM Ticket t WHERE t.ticket_reference_number = ?1 AND t.ticket_active_status = 'ACTIVE' " +
            "AND t.ticket_booking_status = 'NEWLY_BOOKED' AND t.created_at >= CURRENT_TIMESTAMP - INTERVAL 18 HOUR",
            nativeQuery = true)
    Optional<Ticket> findByTicketRefEnterStation(String ticketReferenceNumber);

    @Query("select t from Ticket t where t.ticketReferenceNumber = ?1 AND t.ticketActiveStatus = 'ACTIVE' " +
            "AND t.ticketBookingStatus = 'ENTERED_STATION'")
    Optional<Ticket> findByTicketRefExitStation(String ticketReferenceNumber);

}
