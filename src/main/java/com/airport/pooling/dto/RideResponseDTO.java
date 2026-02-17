package com.airport.pooling.dto;

import com.airport.pooling.entity.RideStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class RideResponseDTO {

    private Long rideId;
    private Long cabId;
    private String driverName;
    private String status;
    private Double totalPrice;
    private Double totalDistance;
    private List<RidePassengerResponseDTO> passengers;
}
