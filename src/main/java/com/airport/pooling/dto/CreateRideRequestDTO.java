package com.airport.pooling.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateRideRequestDTO {

    @NotNull
    private Long passengerId;

    @NotNull
    private Double pickupLat;

    @NotNull
    private Double pickupLng;

    @NotNull
    private Double dropLat;

    @NotNull
    private Double dropLng;

    @NotNull
    private Integer luggageCount;
}
