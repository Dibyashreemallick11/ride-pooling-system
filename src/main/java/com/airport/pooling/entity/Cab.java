package com.airport.pooling.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cabs",
        indexes = {
                @Index(name = "idx_cab_status", columnList = "status")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String driverName;

    @Column(nullable = false)
    private Integer totalSeats;

    @Column(nullable = false)
    private Integer availableSeats;

    @Column(nullable = false)
    private Integer luggageCapacity;

    @Column(nullable = false)
    private Integer availableLuggage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CabStatus status;
}
