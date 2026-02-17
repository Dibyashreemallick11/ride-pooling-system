package com.airport.pooling.service.impl;

import com.airport.pooling.dto.CreateRideRequestDTO;
import com.airport.pooling.dto.RideResponseDTO;
import com.airport.pooling.entity.*;
import com.airport.pooling.exception.ResourceNotFoundException;
import com.airport.pooling.repository.PassengerRepository;
import com.airport.pooling.repository.RideRequestRepository;
import com.airport.pooling.service.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

    private final PassengerRepository passengerRepository;
    private final RideRequestRepository rideRequestRepository;

    @Override
    public RideResponseDTO createRideRequest(CreateRideRequestDTO request) {

        Passenger passenger = passengerRepository.findById(request.getPassengerId())
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not found"));

        RideRequest rideRequest = RideRequest.builder()
                .passenger(passenger)
                .pickupLat(request.getPickupLat())
                .pickupLng(request.getPickupLng())
                .dropLat(request.getDropLat())
                .dropLng(request.getDropLng())
                .luggageCount(request.getLuggageCount())
                .status(RideRequestStatus.PENDING)
                .build();

        // SAVE AND CAPTURE RESPONSE
        RideRequest savedRide = rideRequestRepository.save(rideRequest);

        // RETURN ACTUAL VALUES (NOT NULL)
        return RideResponseDTO.builder()
                .rideId(savedRide.getId())
                .status(savedRide.getStatus().name())
                .totalDistance(0.0)   // Temporary
                .totalPrice(0.0)
                .build();
    }
}
