package com.airport.pooling.controller;

import com.airport.pooling.dto.RideResponseDTO;
import com.airport.pooling.service.RideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rides")
@RequiredArgsConstructor
public class RideController {

    private final RideService rideService;


    @PostMapping("/assign/{rideRequestId}")
    public ResponseEntity<RideResponseDTO> assignRide(
            @PathVariable Long rideRequestId) {

        RideResponseDTO response = rideService.assignRide(rideRequestId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/{rideId}")
    public ResponseEntity<RideResponseDTO> getRide(
            @PathVariable Long rideId) {

        RideResponseDTO response = rideService.getRide(rideId);
        return ResponseEntity.ok(response);
    }
}
