package com.airport.pooling.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatePassengerRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @NotNull
    private Integer detourToleranceMinutes;
}
