package com.airport.pooling.service.impl;
import com.airport.pooling.dto.CreatePassengerRequestDTO;
import com.airport.pooling.dto.PassengerResponseDTO;
import com.airport.pooling.entity.Passenger;
import com.airport.pooling.exception.ResourceNotFoundException;
import com.airport.pooling.repository.PassengerRepository;
import com.airport.pooling.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;

    @Override
    public PassengerResponseDTO createPassenger(CreatePassengerRequestDTO request) {

        Passenger passenger = Passenger.builder()
                .name(request.getName())
                .phone(request.getPhone())
                .detourToleranceMinutes(request.getDetourToleranceMinutes())
                .build();

        Passenger saved = passengerRepository.save(passenger);

        return PassengerResponseDTO.builder()
                .id(saved.getId())
                .name(saved.getName())
                .phone(saved.getPhone())
                .detourToleranceMinutes(saved.getDetourToleranceMinutes())
                .build();
    }

    @Override
    public PassengerResponseDTO getPassenger(Long id) {

        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Passenger not found"));

        return PassengerResponseDTO.builder()
                .id(passenger.getId())
                .name(passenger.getName())
                .phone(passenger.getPhone())
                .detourToleranceMinutes(passenger.getDetourToleranceMinutes())
                .build();
    }
}

