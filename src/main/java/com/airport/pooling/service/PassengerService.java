package com.airport.pooling.service;

import com.airport.pooling.dto.CreatePassengerRequestDTO;
import com.airport.pooling.dto.PassengerResponseDTO;

public interface PassengerService {

    PassengerResponseDTO createPassenger(CreatePassengerRequestDTO request);

    PassengerResponseDTO getPassenger(Long id);
}
