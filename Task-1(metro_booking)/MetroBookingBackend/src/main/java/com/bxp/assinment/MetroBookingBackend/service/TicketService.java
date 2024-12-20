package com.bxp.assinment.MetroBookingBackend.service;

import com.bxp.assinment.MetroBookingBackend.Dto.BookingRequestDto;
import com.bxp.assinment.MetroBookingBackend.Dto.BookingResponseDto;
import com.bxp.assinment.MetroBookingBackend.Dto.EnterExistStationResponseDto;
import com.bxp.assinment.MetroBookingBackend.constants.TicketConstants;
import com.bxp.assinment.MetroBookingBackend.enums.ActiveStatus;
import com.bxp.assinment.MetroBookingBackend.enums.TicketStatus;
import com.bxp.assinment.MetroBookingBackend.model.Passenger;
import com.bxp.assinment.MetroBookingBackend.model.Station;
import com.bxp.assinment.MetroBookingBackend.model.Ticket;
import com.bxp.assinment.MetroBookingBackend.repository.PassengerRepository;
import com.bxp.assinment.MetroBookingBackend.repository.StationRepository;
import com.bxp.assinment.MetroBookingBackend.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final PassengerRepository passengerRepository;
    private final StationRepository stationRepository;

    public BookingResponseDto bookTicket(BookingRequestDto requestDto) {
        //check if passenger exist
        Passenger passenger = passengerRepository.findPassengerByEmailOrMobileNumber(
                requestDto.getPassengerEmail(), requestDto.getPassengerMobile()
        );

        //fetch stations and check if stations exist
        //doing multiple db calls might be slow but caching makes it better as we need to make sure user enter correct station numbers
        Station fromStation = stationRepository.findStationByStationNumber(requestDto.getFromStationNumber());
        Station toStation = stationRepository.findStationByStationNumber(requestDto.getToStationNumber());

        //calculate price
        Double price = calculatePrice(fromStation, toStation);

        String ticketReferenceNumber = generateTicketReferenceNumber();

        Ticket ticket = mapTicket(passenger, fromStation, toStation, price, ticketReferenceNumber);
        ticketRepository.saveTicket(ticket);
        return new BookingResponseDto(ticketReferenceNumber,
                price,
                TicketConstants.TICKET_BOOKED_SUCCESSFULLY);
    }

    public EnterExistStationResponseDto enterStation(String ticketReferenceNumber) {
        //check if the ticket booked is within 18hrs
        //check if the ticket is active and not used before
        Ticket ticket = ticketRepository.findByTicketRefEnterStation(ticketReferenceNumber);
        ticket.setTicketBookingStatus(TicketStatus.ENTERED_STATION);
        ticketRepository.saveTicket(ticket);
        return new EnterExistStationResponseDto(TicketConstants.ENTERED_STATION_SUCCESSFULLY);
    }

    //after exiting station ticket becomes inactive
    public EnterExistStationResponseDto exitStation(String ticketReferenceNumber) {
        //check if the ticket is active and not used before
        //also check if the passenger has already entered the station
        Ticket ticket = ticketRepository.findByTicketRefExitStation(ticketReferenceNumber);
        ticket.setTicketBookingStatus(TicketStatus.EXITED_STATION);
        ticket.setTicketActiveStatus(ActiveStatus.INACTIVE);
        ticketRepository.saveTicket(ticket);
        return new EnterExistStationResponseDto(TicketConstants.EXITED_STATION_SUCCESSFULLY);
    }

    private Ticket mapTicket(
            Passenger passenger,
            Station fromStation,
            Station toStation,
            Double price,
            String ticketReferenceNumber) {
        Ticket ticket = new Ticket();
        ticket.setTicketPrice(price);
        ticket.setFromStation(fromStation);
        ticket.setToStation(toStation);
        ticket.setTicketBookingStatus(TicketStatus.NEWLY_BOOKED);
        ticket.setTicketReferenceNumber(ticketReferenceNumber);
        ticket.setTicketActiveStatus(ActiveStatus.ACTIVE);
        ticket.setPassenger(passenger);
        return ticket;
    }

    private Double calculatePrice(Station fromStation, Station toStation) {
        return Math.abs(fromStation.getPrice() - toStation.getPrice());
    }

    private String generateTicketReferenceNumber() {
        Random random = new Random();
        String alphanumeric = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder ticketReferenceId = new StringBuilder(7);

        for (int i = 0; i < 7; i++) {
            int index = random.nextInt(alphanumeric.length());
            char randomChar = alphanumeric.charAt(index);
            ticketReferenceId.append(randomChar);
        }

        return ticketReferenceId.toString();
    }

}
