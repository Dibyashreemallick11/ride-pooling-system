package com.airport.pooling.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class RidePassengerResponseDTO {

    private Long passengerId;
    private String passengerName;
    private BigDecimal individualPrice;
    private Integer pickupOrder;
    private Integer dropOrder;
}
