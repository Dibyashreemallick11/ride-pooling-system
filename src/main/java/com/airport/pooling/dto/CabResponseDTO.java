package com.airport.pooling.dto;

import com.airport.pooling.entity.CabStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CabResponseDTO {

    private Long id;
    private String driverName;
    private Integer totalSeats;
    private Integer availableSeats;
    private Integer luggageCapacity;
    private Integer availableLuggage;
    private CabStatus status;
}
