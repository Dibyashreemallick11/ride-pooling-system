package com.airport.pooling.controller;

import com.airport.pooling.dto.CreatePassengerRequestDTO;
import com.airport.pooling.dto.PassengerResponseDTO;
import com.airport.pooling.service.PassengerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/passengers")
@RequiredArgsConstructor
public class PassengerController {

    private final PassengerService passengerService;

    @PostMapping
    public ResponseEntity<PassengerResponseDTO> createPassenger(
            @Valid @RequestBody CreatePassengerRequestDTO request) {

        return ResponseEntity.ok(passengerService.createPassenger(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassengerResponseDTO> getPassenger(@PathVariable Long id) {

        return ResponseEntity.ok(passengerService.getPassenger(id));
    }
}
