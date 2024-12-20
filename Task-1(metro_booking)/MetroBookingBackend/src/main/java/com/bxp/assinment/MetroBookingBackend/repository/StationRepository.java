package com.bxp.assinment.MetroBookingBackend.repository;

import com.bxp.assinment.MetroBookingBackend.dao.StationDao;
import com.bxp.assinment.MetroBookingBackend.exception.StationNotFoundException;
import com.bxp.assinment.MetroBookingBackend.model.Station;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class StationRepository {
    private final StationDao stationDao;

    public void saveAll(List<Station> stationList) {
        stationDao.saveAll(stationList);
    }

    public List<Station> findAll() {
        return (List<Station>) stationDao.findAll();
    }

    public Station findStationByStationNumber(Long stationNumber) {
        return stationDao.findByStationNumber(stationNumber)
                .orElseThrow(StationNotFoundException::new);
    }
}
