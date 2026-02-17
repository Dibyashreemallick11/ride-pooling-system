package com.airport.pooling.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PassengerResponseDTO {

    private Long id;
    private String name;
    private String phone;
    private Integer detourToleranceMinutes;
}
