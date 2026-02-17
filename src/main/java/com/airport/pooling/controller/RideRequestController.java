package com.airport.pooling.controller;

import com.airport.pooling.dto.CreateRideRequestDTO;
import com.airport.pooling.dto.RideResponseDTO;
import com.airport.pooling.service.RideRequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rides")
@RequiredArgsConstructor
public class RideRequestController {

    private final RideRequestService rideRequestService;

    @PostMapping("/request")
    public ResponseEntity<RideResponseDTO> createRideRequest(
            @Valid @RequestBody CreateRideRequestDTO request) {

        return ResponseEntity.ok(rideRequestService.createRideRequest(request));
    }
}
