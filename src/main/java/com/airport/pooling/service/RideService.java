package com.airport.pooling.service;

import com.airport.pooling.dto.RideResponseDTO;

public interface RideService {

    RideResponseDTO assignRide(Long rideRequestId);

    RideResponseDTO getRide(Long rideId);
}
