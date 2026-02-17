package com.airport.pooling.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddCabRequestDTO {

    @NotBlank
    private String driverName;

    @NotNull
    @Min(1)
    private Integer totalSeats;

    @NotNull
    @Min(0)
    private Integer luggageCapacity;
}
