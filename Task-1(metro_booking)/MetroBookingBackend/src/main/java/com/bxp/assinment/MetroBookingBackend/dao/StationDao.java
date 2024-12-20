package com.bxp.assinment.MetroBookingBackend.dao;

import com.bxp.assinment.MetroBookingBackend.model.Station;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StationDao extends CrudRepository<Station, Long> {
    Optional<Station> findByStationNumber(Long stationNumber);
}
