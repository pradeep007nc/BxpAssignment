package com.bxp.assinment.MetroBookingBackend.controller;

import com.bxp.assinment.MetroBookingBackend.Dto.EnterExistStationResponseDto;
import com.bxp.assinment.MetroBookingBackend.Dto.StationResponseDto;
import com.bxp.assinment.MetroBookingBackend.service.StationService;
import com.bxp.assinment.MetroBookingBackend.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/station")
public class StationController {
    private final StationService stationService;

    @GetMapping("/list")
    public List<StationResponseDto> getStations(){
        return stationService.fetchAllStations();
    }

    @GetMapping("/enter")
    public EnterExistStationResponseDto enterStation(@RequestParam String ticketReferenceNumber){
        return stationService.enterStation(ticketReferenceNumber);
    }

    @GetMapping("/exit")
    public EnterExistStationResponseDto exitStation(@RequestParam String ticketReferenceNumber){
        return stationService.exitStation(ticketReferenceNumber);
    }

}
