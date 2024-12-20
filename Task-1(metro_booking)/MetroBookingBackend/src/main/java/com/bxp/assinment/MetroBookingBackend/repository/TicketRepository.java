package com.bxp.assinment.MetroBookingBackend.repository;

import com.bxp.assinment.MetroBookingBackend.dao.TicketDao;
import com.bxp.assinment.MetroBookingBackend.exception.TicketException;
import com.bxp.assinment.MetroBookingBackend.model.Ticket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class TicketRepository {
    private final TicketDao ticketDao;

    public void saveTicket(Ticket ticket) {
        ticketDao.save(ticket);
    }

    public Ticket findByTicketRefEnterStation(String ticketReferenceNumber) {
        return ticketDao.findByTicketRefEnterStation(ticketReferenceNumber)
                .orElseThrow(TicketException::new);
    }

    public Ticket findByTicketRefExitStation(String ticketReferenceNumber) {
        return ticketDao.findByTicketRefExitStation(ticketReferenceNumber)
                .orElseThrow(TicketException::new);
    }


}
