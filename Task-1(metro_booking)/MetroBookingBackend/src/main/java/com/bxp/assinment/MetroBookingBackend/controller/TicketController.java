package com.bxp.assinment.MetroBookingBackend.controller;

import com.bxp.assinment.MetroBookingBackend.Dto.BookingRequestDto;
import com.bxp.assinment.MetroBookingBackend.Dto.BookingResponseDto;
import com.bxp.assinment.MetroBookingBackend.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/book")
    public BookingResponseDto bookTicket(@RequestBody BookingRequestDto requestDto) {
        return ticketService.bookTicket(requestDto);
    }

}
