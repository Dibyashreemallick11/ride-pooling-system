package com.airport.pooling.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CancelRideRequestDTO {

    @NotNull
    private Long rideRequestId;
}
