package com.airport.pooling.service.impl;

import com.airport.pooling.dto.AddCabRequestDTO;
import com.airport.pooling.dto.CabResponseDTO;
import com.airport.pooling.entity.Cab;
import com.airport.pooling.entity.CabStatus;
import com.airport.pooling.repository.CabRepository;
import com.airport.pooling.service.CabService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CabServiceImpl implements CabService {

    private final CabRepository cabRepository;

    @Override
    public CabResponseDTO addCab(AddCabRequestDTO request) {

        Cab cab = Cab.builder()
                .driverName(request.getDriverName())
                .totalSeats(request.getTotalSeats())
                .availableSeats(request.getTotalSeats())
                .luggageCapacity(request.getLuggageCapacity())
                .availableLuggage(request.getLuggageCapacity())
                .status(CabStatus.AVAILABLE)
                .build();

        Cab saved = cabRepository.save(cab);

        return mapToDTO(saved);
    }

    @Override
    public List<CabResponseDTO> getAvailableCabs() {

        return cabRepository.findByStatus(CabStatus.AVAILABLE)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private CabResponseDTO mapToDTO(Cab cab) {
        return CabResponseDTO.builder()
                .id(cab.getId())
                .driverName(cab.getDriverName())
                .totalSeats(cab.getTotalSeats())
                .availableSeats(cab.getAvailableSeats())
                .luggageCapacity(cab.getLuggageCapacity())
                .availableLuggage(cab.getAvailableLuggage())
                .status(cab.getStatus())
                .build();
    }
}
