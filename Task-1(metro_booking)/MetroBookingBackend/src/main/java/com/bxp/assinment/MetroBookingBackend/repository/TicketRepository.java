package com.bxp.assinment.MetroBookingBackend.repository;

import com.bxp.assinment.MetroBookingBackend.dao.TicketDao;
import com.bxp.assinment.MetroBookingBackend.exception.TicketException;
import com.bxp.assinment.MetroBookingBackend.exception.TicketTimedOutException;
import com.bxp.assinment.MetroBookingBackend.model.Ticket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@Slf4j
@RequiredArgsConstructor
public class TicketRepository {
    private final TicketDao ticketDao;

    public void saveTicket(Ticket ticket) {
        ticketDao.save(ticket);
    }

    public Ticket findByTicketRefEnterStation(String ticketReferenceNumber) {
        Ticket ticket = ticketDao.findByTicketRefEnterStation(ticketReferenceNumber)
                .orElseThrow(TicketException::new);
        if (ticket.getCreatedAt().isBefore(LocalDateTime.now().minusHours(18))) {
            throw new TicketTimedOutException();
        }
        return ticket;
    }

    public Ticket findByTicketRefExitStation(String ticketReferenceNumber) {
        return ticketDao.findByTicketRefExitStation(ticketReferenceNumber)
                .orElseThrow(TicketException::new);
    }


}
