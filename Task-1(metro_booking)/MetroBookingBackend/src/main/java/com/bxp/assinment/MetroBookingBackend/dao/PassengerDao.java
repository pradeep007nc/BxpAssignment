package com.bxp.assinment.MetroBookingBackend.dao;

import com.bxp.assinment.MetroBookingBackend.model.Passenger;
import org.springframework.data.repository.CrudRepository;

public interface PassengerDao extends CrudRepository<Passenger, Long> {
}
