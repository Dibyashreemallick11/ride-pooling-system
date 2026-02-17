package com.airport.pooling.service;

import com.airport.pooling.dto.AddCabRequestDTO;
import com.airport.pooling.dto.CabResponseDTO;

import java.util.List;

public interface CabService {

    CabResponseDTO addCab(AddCabRequestDTO request);

    List<CabResponseDTO> getAvailableCabs();
}
