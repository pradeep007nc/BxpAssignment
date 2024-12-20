package com.bxp.assinment.MetroBookingBackend.service;

import com.bxp.assinment.MetroBookingBackend.Dto.StationResponseDto;
import com.bxp.assinment.MetroBookingBackend.model.Station;
import com.bxp.assinment.MetroBookingBackend.repository.StationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StationService {
    private final StationRepository stationRepository;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void populateStations() throws IOException {
        try {
            Resource resource = new ClassPathResource("static/stations.json");
            Station[] stations = objectMapper.readValue(resource.getInputStream(), Station[].class);
            List<Station> stationList = Arrays.asList(stations);
            stationRepository.saveAll(stationList);
            log.info("Successfully loaded {} stations from JSON", stationList.size());
        } catch (IOException e) {
            log.error("Failed to load stations from JSON", e);
            throw e;
        }
    }

    public List<StationResponseDto> fetchAllStations(){
        return stationRepository.findAll().stream().map(
                station ->
                        new StationResponseDto(
                                station.getStationName(),
                                station.getPrice(),
                                station.getStationNumber()))
                .toList();
    }

}