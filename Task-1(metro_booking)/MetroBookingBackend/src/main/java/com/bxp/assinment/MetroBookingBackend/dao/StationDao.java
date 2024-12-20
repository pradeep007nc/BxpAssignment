package com.bxp.assinment.MetroBookingBackend.dao;

import com.bxp.assinment.MetroBookingBackend.model.Station;
import org.springframework.data.repository.CrudRepository;

public interface StationDao extends CrudRepository<Station, Long> {
}
