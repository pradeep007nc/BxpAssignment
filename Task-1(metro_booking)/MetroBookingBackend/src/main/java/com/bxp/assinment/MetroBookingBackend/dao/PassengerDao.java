package com.bxp.assinment.MetroBookingBackend.dao;

import com.bxp.assinment.MetroBookingBackend.model.Passenger;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PassengerDao extends CrudRepository<Passenger, Long> {
    Optional<Passenger> findByEmail(String email);

    Optional<Passenger> findByPhone(String phoneNumber);
}
