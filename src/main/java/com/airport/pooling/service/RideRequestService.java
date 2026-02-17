package com.airport.pooling.service;

import com.airport.pooling.dto.CreateRideRequestDTO;
import com.airport.pooling.dto.RideResponseDTO;

public interface RideRequestService {

    RideResponseDTO createRideRequest(CreateRideRequestDTO request);
}
