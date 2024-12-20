package com.bxp.assinment.MetroBookingBackend.service;

import com.bxp.assinment.MetroBookingBackend.model.Passenger;
import com.bxp.assinment.MetroBookingBackend.repository.PassengerRepository;
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
public class PassengerService {
    private final PassengerRepository passengerRepository;
    private final ObjectMapper objectMapper;

    @PostConstruct
    public void populateUsers() throws IOException {
        try {
            // Load the users.json file from the classpath
            Resource resource = new ClassPathResource("static/passengers.json");

            // Deserialize JSON into an array of User objects
            Passenger[] users = objectMapper.readValue(resource.getInputStream(), Passenger[].class);

            // Convert the array into a list
            List<Passenger> userList = Arrays.asList(users);

            // Save all users into the repository
            passengerRepository.saveAll(userList);

            log.info("Successfully loaded {} users from JSON", userList.size());
        } catch (IOException e) {
            log.error("Failed to load users from JSON", e);
            throw e;
        }
    }
}
