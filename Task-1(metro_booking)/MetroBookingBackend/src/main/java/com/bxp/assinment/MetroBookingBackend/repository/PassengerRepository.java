package com.bxp.assinment.MetroBookingBackend.repository;

import com.bxp.assinment.MetroBookingBackend.dao.PassengerDao;
import com.bxp.assinment.MetroBookingBackend.exception.EnterDetailsException;
import com.bxp.assinment.MetroBookingBackend.exception.PassengerNotFoundException;
import com.bxp.assinment.MetroBookingBackend.model.Passenger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class PassengerRepository {

    private final PassengerDao passengerDao;

    public void saveAll(List<Passenger> userList) {
        passengerDao.saveAll(userList);
    }

    public Passenger findPassengerByEmailOrMobileNumber(String email, String phoneNumber) {
        if (email != null) {
            return passengerDao.findByEmail(email).orElseThrow(PassengerNotFoundException::new);
        } else if (phoneNumber != null)
            return passengerDao.findByPhone(phoneNumber).orElseThrow(PassengerNotFoundException::new);
        else
            throw new EnterDetailsException();
    }

}
