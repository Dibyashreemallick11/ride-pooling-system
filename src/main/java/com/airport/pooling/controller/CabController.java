package com.airport.pooling.controller;

import com.airport.pooling.dto.AddCabRequestDTO;
import com.airport.pooling.dto.CabResponseDTO;
import com.airport.pooling.service.CabService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cabs")
@RequiredArgsConstructor
public class CabController {

    private final CabService cabService;

    @PostMapping
    public ResponseEntity<CabResponseDTO> addCab(
            @Valid @RequestBody AddCabRequestDTO request) {

        return ResponseEntity.ok(cabService.addCab(request));
    }

    @GetMapping("/available")
    public ResponseEntity<List<CabResponseDTO>> getAvailableCabs() {

        return ResponseEntity.ok(cabService.getAvailableCabs());
    }
}
